package com.ntt.service.impl;

import com.ntt.hibernate.Entity.FileContent;
import com.ntt.hibernate.Entity.StockDetails;
import com.ntt.hibernate.dao.FileContentDao;
import com.ntt.service.AsyncService;
import com.ntt.service.FileContentService;
import com.ntt.model.FileContentDTO;
import com.ntt.model.FileStatus;
import com.ntt.model.UploadFileResponseDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link FileContent}.
 */
@Service
@Transactional
public class FileContentServiceImpl implements FileContentService {

    private final Logger log = LoggerFactory.getLogger(FileContentServiceImpl.class);

    private final FileContentDao fileContentRepository;

    private final AsyncService asyncService;

    public FileContentServiceImpl(FileContentDao fileContentRepository,AsyncService asyncService) {
		this.asyncService = asyncService;
		this.fileContentRepository = fileContentRepository;
    }

    /**
     * Save a fileContent.
     *
     * @param fileContent the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FileContent save(FileContent fileContent) {
        log.debug("Request to save FileContent : {}", fileContent);
        return fileContentRepository.save(fileContent);
    }

    /**
     * Get all the fileContents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FileContent> findAll(Pageable pageable) {
        log.debug("Request to get all FileContents");
        return fileContentRepository.findAll(pageable);
    }


    /**
     * Get one fileContent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
   @Transactional (readOnly = true)
    public Optional<FileContent> findOne(Long id) {
        log.debug("Request to get FileContent : {}", id);
        return fileContentRepository.findById(id);
    }

	@Override
	public FileContent upload(MultipartFile fileContent) {
		// TODO Auto-generated method stub
			FileContent fileContent2 = new FileContent();
				
			fileContent2.setFileName(fileContent.getOriginalFilename());
			fileContent2.setFileSize(fileContent.getSize());
		//	fileContent2.setContent(fileContent.getBytes());
			fileContent2.setType(fileContent.getContentType());
			fileContent2.setGuid(UUID.randomUUID());
			String status = FileStatus.PROCESSING.toString();
			fileContent2.setStatus(status);
			fileContentRepository.save(fileContent2);
			try {
				multipartToFile(fileContent,fileContent2);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return fileContent2;
	}
	
	public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
	
	private StockDetails  getNewStockDetails(String[] siteItem, UUID uuid) {
		StockDetails stockDetails = null;
		if( null != siteItem) {
			
				stockDetails =new StockDetails();
				stockDetails.setNpi(siteItem[0]);
				stockDetails.setNdc(siteItem[1]);
				stockDetails.setUnits(siteItem[2]);
				stockDetails.setUnitsCost(siteItem[3]);
				stockDetails.setGuid(uuid);
			
		}
		return stockDetails;
	}
	
	public static String getTemFile(FileContent fileContent) {
		
		return System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+fileContent.getGuid()+".txt";
	}
	public  static void  multipartToFile(MultipartFile multipart, FileContent fileContent) throws IllegalStateException, IOException {
	    File convFile = new File(getTemFile(fileContent));
	    FileUtils.writeByteArrayToFile(convFile, multipart.getBytes());
	   // multipart.transferTo(convFile);
	    
	   
	}

	@Async("taskExecutor")
	@Override
	public void parsingFile(MultipartFile file,FileContent fileContent) throws InterruptedException {
		
		 log.debug("Entering parsingFile() FileContent : {}");
		 		 /*
		  NPI|NDC|Units|UnitCost
		0000000009|00000001465|2555|258.72
		  */
		 Thread.sleep(30000L);
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
				        StockDetails stockDetails = getNewStockDetails(siteItem,fileContent.getGuid());
				        if( stockDetails != null)
							asyncService.saveStockDetails(stockDetails);
				        if(!(isNumeric(siteItem[2]) &&  isNumeric(siteItem[3]))) {
				        	invalidRecordCount++;
				        	
				        }
				     }
				} finally {
				    it.close();
				    FileUtils.deleteQuietly(tempFile);
				}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 log.debug("Exiting  parsingFile() IOException : {}");
		}
		 Optional<FileContent> record = fileContentRepository.findById(fileContent.getId());
		 FileContent finalRecord = record.get();
		 finalRecord.setStatus(FileStatus.COMPLETED.toString());
		 finalRecord.setInvalidRecordCount(invalidRecordCount);
		 finalRecord.setTotalRecordCount(totalRecordCount);
		 asyncService.saveFileContent(finalRecord);
		 log.debug("Exiting  parsingFile() FileContent : {}");
		 log.debug("ParsingFile() validRecordCount : {}"+ validRecordCount);
		 log.debug("ParsingFile() invalidRecordCount : {}"+ invalidRecordCount);
		 log.debug("ParsingFile() totalRecordCount : {}"+ totalRecordCount);
		
	}

	@Override
	public  Optional<FileContent> findByGuid(String guid) {
		// TODO Auto-generated method stub
		
		return fileContentRepository.findByGuid( UUID.fromString(guid));
	}

		@Override
	public UploadFileResponseDTO createUploadFileResponseDTO(FileContent result) {
		// TODO Auto-generated method stub
 		//
		UploadFileResponseDTO uploadFileResponseDTO = new UploadFileResponseDTO();
        uploadFileResponseDTO.setFileName(result.getFileName());
        uploadFileResponseDTO.setGuid(result.getGuid().toString());
        uploadFileResponseDTO.setStatus(FileStatus.PROCESSING);
        return uploadFileResponseDTO;
	}

	@Override
	public FileContentDTO createFileContentDTO(FileContent fileContent) {
		// TODO Auto-generated method stub
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
		if( fileContent.getStatus().equalsIgnoreCase(FileStatus.PROCESSING.toString())) {
			fileContentDTO.setTotalRecordCount(0L);
			fileContentDTO.setValidRecordCount(0L);
			fileContentDTO.setInvalidRecordCount(0L);
		}
		return fileContentDTO;
	}
}

