package com.jetbrains.shared.fileStorage

import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/files")
class FileStorageController(private val fileStorageService: FileStorageService) {
    @GetMapping("download/{fileName}")
    fun downloadFile(@PathVariable fileName: String): ResponseEntity<Resource> {
        val resource = fileStorageService.downloadFile(fileName)
        val fileExtension = fileStorageService.getFileExtension(fileName)

        return ResponseEntity.ok()
            .contentType(MediaType.valueOf("image/$fileExtension"))
            .body(resource)
    }

    @DeleteMapping("{fileName}")
    fun deleteFile(@PathVariable fileName: String): ResponseEntity<Unit> {
        fileStorageService.deleteFile(fileName)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("upload")
    fun uploadFile(@RequestParam(value = "file") file: MultipartFile): ResponseEntity<Map<String, String>> {
        val fileName = fileStorageService.uploadFile(file)
        return ResponseEntity(mapOf("file_name" to fileName), HttpStatus.CREATED)
    }
}