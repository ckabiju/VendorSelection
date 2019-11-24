package com.ntt.hibernate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntt.hibernate.Entity.FileContent;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileContentDao extends JpaRepository<FileContent, Long> {

    public Optional<FileContent> findByGuid(UUID guid);
}
