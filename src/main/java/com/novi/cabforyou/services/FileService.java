package com.novi.cabforyou.services;

import com.novi.cabforyou.exceptions.FileNotFoundException;
import com.novi.cabforyou.models.FileUpAndDownload;
import com.novi.cabforyou.repositories.FileUpAndDownloadRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

@Service
public class FileService {

    private final FileUpAndDownloadRepository fileUpAndDownloadRepository;

    public FileService(FileUpAndDownloadRepository fileUpAndDownloadRepository) {
        this.fileUpAndDownloadRepository = fileUpAndDownloadRepository;
    }

    public Collection<FileUpAndDownload> getALlFromDB() {
        return fileUpAndDownloadRepository.findAll();
    }

    public FileUpAndDownload uploadFileDocument(MultipartFile file) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileUpAndDownload fileUpAndDownload = new FileUpAndDownload();
        fileUpAndDownload.setFileName(name);
        fileUpAndDownload.setDocFile(file.getBytes());

        fileUpAndDownloadRepository.save(fileUpAndDownload);

        return fileUpAndDownload;

    }

    public FileUpAndDownload singleFileDownload(String fileName, HttpServletRequest request) {

        FileUpAndDownload document = fileUpAndDownloadRepository.findByFileName(fileName);

        String mimeType = request.getServletContext().getMimeType(document.getFileName());

        return document;

    }

    public Resource downLoadFile(String fileName) {

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(fileName).toUriString();

        Resource resource;

        try {
            resource = new UrlResource(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Issue in reading the file", e);
        }

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new FileNotFoundException("The file doesn't exist or not readable");
        }
    }

}


