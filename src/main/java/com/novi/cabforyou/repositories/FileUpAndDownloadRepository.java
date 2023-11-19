package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.FileUpAndDownload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUpAndDownloadRepository extends JpaRepository<FileUpAndDownload, Long> {
    FileUpAndDownload findByFileName(String fileName);
}
