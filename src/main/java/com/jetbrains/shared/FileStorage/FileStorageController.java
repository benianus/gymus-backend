package com.jetbrains.shared.FileStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
