package com.jetbrains.shared.FileStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/files")
public class FileStorageController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        var resource = fileStorageService.downloadFile(fileName);
        return ResponseEntity.ok().body(resource);
    }

    @DeleteMapping("{fileName}")
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        fileStorageService.deleteFile(fileName);
        return ResponseEntity.notFound().build();
    }

}
