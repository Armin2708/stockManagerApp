package com.insuranceApp.clientFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insuranceApp.insuranceClient.InsuranceClient;
import jakarta.persistence.*;

@Entity
@Table(
        name = "file"
)
public class ClientFile {

    @Id
    @SequenceGenerator(
            name = "file_id_seq",
            sequenceName = "file_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "file_id_seq"
    )
    private Integer id;

    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonIgnore
    private InsuranceClient client;

    @Column(name = "file_name",
            nullable = false
    )
    private String fileName;

    @Column(name = "file_size",
            nullable = false
    )
    private Long fileSize;

    @Column(name = "file_format",
            nullable = false
    )
    private String fileFormat;

    @Column(name = "file_content")
    private byte[] fileContent;


    public ClientFile() {}
    public ClientFile(InsuranceClient client, String fileName, Long fileSize, String fileFormat, byte[] fileContent){
        this.client=client;
        this.fileName=fileName;
        this.fileSize=fileSize;
        this.fileFormat=fileFormat;
        this.fileContent=fileContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InsuranceClient getClientId() {
        return client;
    }

    public void setClientId(InsuranceClient client) {
        this.client = client;
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

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

}

