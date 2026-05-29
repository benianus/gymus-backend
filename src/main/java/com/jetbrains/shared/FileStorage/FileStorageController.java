package com.jetbrains.shared.FileStorage;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/files")
public class FileStorageController {

    private final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        var resource = fileStorageService.downloadFile(fileName);
        var fileExtension = fileStorageService.getFileExtension(fileName);
        return ResponseEntity.ok()
                             .contentType(MediaType.valueOf("image/" + fileExtension))
                             .body(resource);
    }

    @DeleteMapping("{fileName}")
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        fileStorageService.deleteFile(fileName);
        return ResponseEntity.notFound().build();
    }

}
