package com.jetbrains.shared.FileStorage

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile

interface FileStorageService {
    fun storeFile(file: MultipartFile?): String

    fun downloadFile(fileName: String): Resource

    fun deleteFile(fileName: String)

    fun getFileExtension(fileName: String): String
}
