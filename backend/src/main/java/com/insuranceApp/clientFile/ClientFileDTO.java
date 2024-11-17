package com.insuranceApp.clientFile;

public class ClientFileDTO {
    private Integer id;
    private String fileName;
    private Long fileSize;

    public ClientFileDTO(Integer id, String fileName, Long fileSize) {
        this.id = id;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
