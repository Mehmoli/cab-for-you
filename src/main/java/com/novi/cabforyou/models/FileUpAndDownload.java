package com.novi.cabforyou.models;

import jakarta.persistence.*;

@Entity
public class FileUpAndDownload {

    @Id
    @GeneratedValue
    private  long id;
    private String fileName;

    @Lob
    private byte[] docFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }
}
