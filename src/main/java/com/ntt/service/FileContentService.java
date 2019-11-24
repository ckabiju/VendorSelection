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
