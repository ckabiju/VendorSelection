package com.ntt.service;

import com.ntt.hibernate.Entity.FileContent;
import com.ntt.model.FileContentDTO;
import com.ntt.model.UploadFileResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FileContent}.
 */
public interface FileContentService {

    /**
     * Save a fileContent.
     *
     * @param fileContent the entity to save.
     * @return the persisted entity.
     */
    FileContent save(FileContent fileContent);

    /**
     * Get all the fileContents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileContent> findAll(Pageable pageable);


    /**
     * Get the "id" fileContent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileContent> findOne(Long id);

    /**
     * Upload file.
     *
     * @param file the file need to be uploaded.
     * @return the entity.
     */
    FileContent upload(MultipartFile file);

    /**
     *
     *
     * @param file the file need to be uploaded.
     * @return the entity.
     */
	void parsingFile(MultipartFile file, FileContent result) throws InterruptedException;
	
    Optional<FileContent> findByGuid(String guid);
	UploadFileResponseDTO createUploadFileResponseDTO(FileContent result);

	FileContentDTO createFileContentDTO(FileContent fileContent);
}
