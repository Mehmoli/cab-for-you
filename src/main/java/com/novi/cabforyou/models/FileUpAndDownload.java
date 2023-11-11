package com.novi.cabforyou.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class FileUpAndDownload {

    @Id
    private String fileName;

    private String contentType;

    @Lob
    private byte[] docFile;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }
}
