package com.novi.cabforyou.controllers;

import com.novi.cabforyou.FileUpAndDownloadResponse.FileUpAndDownloadResponse;
import com.novi.cabforyou.models.FileUpAndDownload;
import com.novi.cabforyou.services.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@CrossOrigin
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/getAll")
    public Collection<FileUpAndDownload> getAllFromDB() {
        return fileService.getALlFromDB();
    }

    @PostMapping("single/uploadDb")
    public FileUpAndDownloadResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        FileUpAndDownload fileUpAndDownload = fileService.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUpAndDownloadResponse(fileUpAndDownload.getFileName(), url, contentType);
    }

    @GetMapping("/download/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        FileUpAndDownload document = fileService.singleFileDownload(fileName, request);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(document.getDocFile());
    }
}

