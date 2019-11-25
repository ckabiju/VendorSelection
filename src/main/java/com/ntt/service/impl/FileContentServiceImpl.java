package com.ntt.service.impl;

import com.ntt.hibernate.Entity.FileContent;
import com.ntt.hibernate.Entity.Site;
import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.hibernate.dao.FileContentDao;
import com.ntt.hibernate.dao.SiteDao;
import com.ntt.service.AsyncService;
import com.ntt.service.FileContentService;
import com.ntt.model.FileContentDTO;
import com.ntt.model.FileStatus;
import com.ntt.model.UploadFileResponseDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Service Implementation for managing {@link FileContent}.
 */
@Service
@Transactional
public class FileContentServiceImpl implements FileContentService {

    private final Logger log = LoggerFactory.getLogger(FileContentServiceImpl.class);
    private final int NUMBER_OF_FIELDS = 4;
    private final FileContentDao fileContentRepository;
    private final SiteDao siteRespository;
    private final AsyncService asyncService;

    private Set<Site> sites;

    public FileContentServiceImpl(FileContentDao fileContentRepository,AsyncService asyncService,SiteDao siteRespository) {
		this.asyncService = asyncService;
		this.fileContentRepository = fileContentRepository;
		this.siteRespository = siteRespository;
		this.sites = new HashSet<Site>(siteRespository.findAll());

    }


	/**
	 * Upload and parse the file
	 *
	 * @param  fileContent
	 * @return FileContent Entity persist after parsing file
	 */
	@Override
	public FileContent upload(MultipartFile fileContent) {
			FileContent fileContent2 = new FileContent();
			fileContent2.setFileName(fileContent.getOriginalFilename());
			fileContent2.setFileSize(fileContent.getSize());
			fileContent2.setType(fileContent.getContentType());
			fileContent2.setGuid(UUID.randomUUID());
			String status = FileStatus.Processing.toString();
			fileContent2.setStatus(status);
			fileContentRepository.save(fileContent2);
			try {
				multipartToFile(fileContent,fileContent2);
			} catch (IllegalStateException | IOException e) {
				log.debug("Exiting  upload() IOException : {}");
			}
		return fileContent2;
	}


	/**
	 * It checks the validity of the fields to be Numeric or not
	 *
	 * @param  strNum
	 * @return boolean value true/false
	 */

	public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

	/**
	 * Get to StcokDetials Entity
	 *
	 * @param  siteItem  array after reading Line from file
	 * @param  uuid guid to map it to stock-details record
	 * @return stockdetials entity
	 */
	private StockDetails  getNewStockDetails(String[] siteItem, UUID uuid) {
		StockDetails stockDetails = null;
		Site site = null;
		if( null != siteItem) {

				stockDetails =new StockDetails();
				stockDetails.setNpi(siteItem[0]);
				stockDetails.setNdc(siteItem[1]);
				stockDetails.setUnits(siteItem[2]);
				stockDetails.setUnitsCost(siteItem[3]);
				stockDetails.setGuid(uuid);

				site = new Site();
				site.setNpi(siteItem[0]);
				site.setSiteName(siteItem[0]);
				sites.add(site);
		}
		return stockDetails;
	}

	/**
	 * Get to temp path for processing
	 *
	 * @param fileContent the file to be uplodded
	 * @return tempfile patj
	 */

	public static String getTemFile(FileContent fileContent) {
		
		return System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+fileContent.getGuid()+".txt";
	}

	/**
	 * Save file to temp path for processing
	 *
	 * @param multipart the file to be uploaded
	 * @param fileContent the file to be uplodded
	 *
	 */
	public  static void  multipartToFile(MultipartFile multipart, FileContent fileContent) throws IllegalStateException, IOException {
	    File convFile = new File(getTemFile(fileContent));
	    FileUtils.writeByteArrayToFile(convFile, multipart.getBytes());

	   
	}

	/**
	 * Save a fileContent.
	 * Its Async method does parsing a file
	 * @param file the file to be uplodded
	 * @param fileContent the entity to save after parsing file.
	 *
	 */
	@Async("taskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	@Override
	public void parsingFile(MultipartFile file,FileContent fileContent) throws InterruptedException {
		
    	log.debug("Entering parsingFile() FileContent : {}");
		long invalidRecordCount = 0;
		long validRecordCount = 0;
		long totalRecordCount = 0;
		try {
			
			String tempFilePath = getTemFile(fileContent);
			File tempFile = new File(tempFilePath);
			LineIterator it = FileUtils.lineIterator(tempFile);
				
				try {
					if(it.hasNext()) {
						String firstLine = it.nextLine();
					}
				    while (it.hasNext()) {
						String line = it.nextLine();
						totalRecordCount++;
						String[] siteItem = line.split("\\|");
						if (null != siteItem && siteItem.length == NUMBER_OF_FIELDS) {
							StockDetails stockDetails = getNewStockDetails(siteItem, fileContent.getGuid());
							if (stockDetails != null)
								asyncService.saveStockDetails(stockDetails);
							if (!((isNumeric(siteItem[2]) && isNumeric(siteItem[3])))) {
								invalidRecordCount++;
							}
						}else if(line.length() > 0){
							invalidRecordCount++;
						}else if(line.isEmpty()){
							totalRecordCount = totalRecordCount-1;
						}

					}
				} finally {
				    it.close();
				    FileUtils.deleteQuietly(tempFile);
				}
				
		} catch (IOException e) {
			 log.debug("Exiting  parsingFile() IOException : {}");
		}

		 Optional<FileContent> record = fileContentRepository.findById(fileContent.getId());
		 FileContent finalRecord = record.get();
		 finalRecord.setStatus(FileStatus.Completed.toString());
		 finalRecord.setInvalidRecordCount(invalidRecordCount);
		 finalRecord.setTotalRecordCount(totalRecordCount);
		 siteRespository.saveAll(sites);
		 asyncService.saveFileContent(finalRecord);
    }


	/**
	 * Gets fileContent Entity.
	 *
	 * @param guid the
	 * @param fileContent the entity
	 *
	 */

	@Override
	public  Optional<FileContent> findByGuid(String guid) {
		return fileContentRepository.findByGuid( UUID.fromString(guid));
	}


	/*
		Data Transfer Object for Returning Response for upload request

	 */
	@Override
	public UploadFileResponseDTO createUploadFileResponseDTO(FileContent result) {
		UploadFileResponseDTO uploadFileResponseDTO = new UploadFileResponseDTO();
        uploadFileResponseDTO.setFileName(result.getFileName());
        uploadFileResponseDTO.setGuid(result.getGuid().toString());
        uploadFileResponseDTO.setStatus(FileStatus.Processing);
        return uploadFileResponseDTO;
	}

	/*
		Data Transfer Object for FileContent Entity

	 */
	@Override
	public FileContentDTO createFileContentDTO(FileContent fileContent) {

		FileContentDTO fileContentDTO = new FileContentDTO();
		fileContentDTO.setGuid(fileContent.getGuid());
		fileContentDTO.setFileName(fileContent.getFileName());
		fileContentDTO.setStatus(fileContent.getStatus());
		
		if( (null != fileContent.getTotalRecordCount() )&& (null != fileContent.getInvalidRecordCount() )) {
			
			fileContentDTO.setTotalRecordCount(fileContent.getTotalRecordCount());
			fileContentDTO.setInvalidRecordCount(fileContent.getInvalidRecordCount());
			Long validRecordCount = fileContent.getTotalRecordCount() - fileContent.getInvalidRecordCount();
			fileContentDTO.setValidRecordCount(validRecordCount);
		}
		if( fileContent.getStatus().equalsIgnoreCase(FileStatus.Processing.toString())) {
			fileContentDTO.setTotalRecordCount(0L);
			fileContentDTO.setValidRecordCount(0L);
			fileContentDTO.setInvalidRecordCount(0L);
		}
		return fileContentDTO;
	}
}

