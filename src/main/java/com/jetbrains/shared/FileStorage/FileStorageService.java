package com.jetbrains.shared.FileStorage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file);

    Resource downloadFile(String fileName);

    void deleteFile(String fileName);

    String getFileExtension(String fileName);

}
