package com.jetbrains.shared.FileStorage

import com.jetbrains.shared.exceptions.CustomExceptionHandler
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

@Service
class FileStorageServiceImpl(
    private val fileStorageProperties: FileStorageProperties
) : FileStorageService {
    private val rootPath: Path = Paths.get(this.fileStorageProperties.uploadDir)

    init {
        verifyIfDirectoryExistOrCreate()
    }

    @Throws(Exception::class)
    private fun checkFileLength(file: MultipartFile) {
        if (file.isEmpty && file.size == 0L) {
            throw Exception("File is empty")
        }
    }

    private fun changeFileName(fileExtension: String): String {
        return "${UUID.randomUUID()}.${fileExtension}"
    }

    @Throws(Exception::class)
    private fun verifyAllowedFileExtension(file: MultipartFile): String {
        val allowedExtensions = listOf("jpg", "jpeg", "png")

        val fileExtension = file.originalFilename?.let {
            getFileExtension(it)
        } ?: throw Exception("File name is null")

        if (!allowedExtensions.contains(fileExtension)) {
            throw Exception("File extension not supported")
        }

        return fileExtension
    }

    private fun verifyIfDirectoryExistOrCreate() {
        if (Files.notExists(this.rootPath)) {
            try {
                Files.createDirectories(this.rootPath)
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }

    override fun storeFile(file: MultipartFile?): String {
        return file?.let {
            checkFileLength(it)
            val fileExtension = verifyAllowedFileExtension(it)
            val fileName = changeFileName(fileExtension)
            Files.copy(it.inputStream, this.rootPath.resolve(fileName))
            return@let fileName
        } ?: throw CustomExceptionHandler.fileIsEmptyOrNull("File is empty or null")
    }

    override fun downloadFile(fileName: String): Resource {
        try {
            val path = rootPath.resolve(fileName)
            return UrlResource.from(path.toUri())
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e.message)
        }
    }

    override fun deleteFile(fileName: String) {
        try {
            val path = rootPath.resolve(fileName)
            println(path)
            if (Files.exists(path)) {
                path.toFile().delete()
            }
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getFileExtension(fileName: String): String =
        fileName.substring(fileName.lastIndexOf(".") + 1)
}
