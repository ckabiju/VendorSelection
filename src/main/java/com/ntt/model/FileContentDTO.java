package com.ntt.model;

import java.util.UUID;

public class FileContentDTO {
	
   private static final long serialVersionUID = 1L;
   private String fileName;
   private UUID guid;
   private String status;
   private Long totalRecordCount;
   private Long invalidRecordCount;
   private Long validRecordCount;
  
    public Long getValidRecordCount() {
	return validRecordCount;
}
public void setValidRecordCount(Long validRecordCount) {
	this.validRecordCount = validRecordCount;
}
	public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public UUID getGuid() {
        return guid;
    }
    public void setGuid(UUID guid) {
        this.guid = guid;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalRecordCount() {
        return totalRecordCount;
    } 
    public void setTotalRecordCount(Long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }
    public Long getInvalidRecordCount() {
        return invalidRecordCount;
    }
    public void setInvalidRecordCount(Long invalidRecordCount) {
        this.invalidRecordCount = invalidRecordCount;
    }
   
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileContentDTO)) {
            return false;
        }
        return guid != null && guid.equals(((FileContentDTO) o).guid);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FileContent{" +
            ", fileName='" + getFileName() + "'" +
            ", guid='" + getGuid() + "'" +
            ", status='" + getStatus() + "'" +
            ", totalRecordCount=" + getTotalRecordCount() +
            ", invalidRecordCount=" + getInvalidRecordCount() +
            "}";
    }
}
