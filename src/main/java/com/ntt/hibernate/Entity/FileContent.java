package com.ntt.hibernate.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A FileContent.
 */
@Entity
@Table(name = "file_content")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "type")
    private String type;

    @Type(type = "uuid-char")
    @Column(name = "guid", length = 36)
    private UUID guid;

    @Column(name = "status")
    private String status;

    @Column(name = "total_record_count")
    private Long totalRecordCount;

    @Column(name = "invalid_record_count")
    private Long invalidRecordCount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public FileContent fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public FileContent fileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getType() {
        return type;
    }

    public FileContent type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getGuid() {
        return guid;
    }

    public FileContent guid(UUID guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getStatus() {
        return status;
    }

    public FileContent status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalRecordCount() {
        return totalRecordCount;
    }

    public FileContent totalRecordCount(Long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
        return this;
    }

    public void setTotalRecordCount(Long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public Long getInvalidRecordCount() {
        return invalidRecordCount;
    }

    public FileContent invalidRecordCount(Long invalidRecordCount) {
        this.invalidRecordCount = invalidRecordCount;
        return this;
    }

    public void setInvalidRecordCount(Long invalidRecordCount) {
        this.invalidRecordCount = invalidRecordCount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileContent)) {
            return false;
        }
        FileContent obj  = (FileContent) o;
        return (id != null && id.equals(obj.id)) &&
                (( null != fileSize && null != obj.fileSize) && fileSize.equals(obj.fileSize)) &&
                (( null != fileName && null != obj.fileName) && fileName.equals(obj.fileName)) &&
                (( null != invalidRecordCount && null != obj.invalidRecordCount) && invalidRecordCount.equals(obj.invalidRecordCount)) &&
                (( null != totalRecordCount && null != obj.totalRecordCount) && totalRecordCount.equals(obj.totalRecordCount))&&
                (( null != guid && null != obj.guid) && guid.equals(obj.guid)) &&
                (( null != guid && null != obj.guid) && guid.equals(obj.guid));

    }

    @Override
    public int hashCode() {

        int hashCode = 13 ;

        hashCode += 7 * fileName.hashCode();
        hashCode += 7 * fileSize.hashCode();
        hashCode += 7 * invalidRecordCount.hashCode();
        hashCode += 7 * totalRecordCount.hashCode();
        hashCode += 7 * status.hashCode();
        hashCode += 7 * guid.hashCode();

        return 31;
    }

    @Override
    public String toString() {
        return "FileContent{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", fileSize=" + getFileSize() +
            ", type='" + getType() + "'" +
            ", guid='" + getGuid() + "'" +
            ", status='" + getStatus() + "'" +
            ", totalRecordCount=" + getTotalRecordCount() +
            ", invalidRecordCount=" + getInvalidRecordCount() +
            "}";
    }
}
