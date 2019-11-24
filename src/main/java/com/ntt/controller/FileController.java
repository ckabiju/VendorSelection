package com.ntt.controller;

import com.ntt.hibernate.Entity.FileContent;
import com.ntt.service.FileContentService;
import com.ntt.model.FileContentDTO;
import com.ntt.model.UploadFileResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ntt.hibernate.Entity.FileContent}.
 */
@RestController
@RequestMapping("/api")
public class FileController{

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    private final FileContentService fileContentService;

    public FileController(FileContentService fileContentService) {
        this.fileContentService = fileContentService;
    }

    /**
     * {@code POST  /files/qoh} : Upload a File.
     *
     * @param file the file to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileContent, or with status {@code 400 (Bad Request)} if the fileContent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/files/qoh")
    public ResponseEntity<UploadFileResponseDTO> uploadFile(@RequestPart MultipartFile file) throws URISyntaxException {
        log.debug("REST request to save FileContent : {}");
        FileContent result = fileContentService.upload(file);
        UploadFileResponseDTO  uploadFileResponseDTO = fileContentService.createUploadFileResponseDTO(result);
       
        try {
			fileContentService.parsingFile(file, result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.debug("REST request to Parsing Failed Exception : {}");
		}

        return ResponseEntity.created(new URI("/api/files/qoh/" + result.getId())).body(uploadFileResponseDTO);
    }
    
    /**
     * {@code GET  /files/qoh/:id} : get the "id" fileContent.
     *
     * @param id the id of the FileDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileContent, or with status {@code 404 (Not Found)}.
     * @throws URISyntaxException 
     */
    @GetMapping("/files/qoh/{id}")
    public ResponseEntity<FileContentDTO> getFileDetails(@PathVariable String id) throws URISyntaxException {
        log.debug("REST request to get FileContent : {}",id);
        Optional<FileContent> fileContent = fileContentService.findByGuid(id);
        FileContentDTO fileDetails = fileContentService.createFileContentDTO(fileContent.get());
        String guid = fileDetails.getGuid().toString();
        return ResponseEntity.created(new URI("/api/files/qoh/" + guid)).body(fileDetails);
    }

}
