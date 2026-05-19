package com.jetbrains.shared.FileStorage;

import org.jspecify.annotations.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootPath;
    private final FileStorageProperties fileStorageProperties;

    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
        this.rootPath = Paths.get(this.fileStorageProperties.getUploadDir());
        verifyIfExistOrCreateDirectory();
    }

    private static @NonNull String changeFileName(String fileExtension) {
        return UUID.randomUUID() + fileExtension;
    }

    private static void checkFileLength(MultipartFile file) throws Exception {
        if(file.isEmpty() && file.getSize() == 0) {
            throw new Exception("File is empty");
        }
    }

    private static @NonNull String verifyFileAllowedExtension(MultipartFile file) throws Exception {
        var allowedExtensions = List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp");
        var fileExtension = file.getOriginalFilename()
                                .substring(file.getOriginalFilename().lastIndexOf("."));
        if(!allowedExtensions.contains(fileExtension)) {
            throw new Exception("File extension not supported");
        }
        return fileExtension;
    }

    private void verifyIfExistOrCreateDirectory() {
        if(Files.notExists(this.rootPath)) {
            try {
                Files.createDirectories(this.rootPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String storeFile(MultipartFile file) {
        try {

            checkFileLength(file);

            var fileExtension = verifyFileAllowedExtension(file);

            var fileName = changeFileName(fileExtension);

            Files.copy(file.getInputStream(), this.rootPath.resolve(fileName));

            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public Resource downloadFile(String fileName) {
        try {
            var path = rootPath.resolve(fileName);
            return UrlResource.from(path.toUri());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteFile(String fileName) {
        try {
            var path = rootPath.resolve(fileName);
            System.out.println(path);
            if(Files.exists(path)) {
                path.toFile().delete();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
