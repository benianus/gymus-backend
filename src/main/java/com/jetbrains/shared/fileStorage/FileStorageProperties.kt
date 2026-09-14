package com.jetbrains.shared.fileStorage

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "file")
data class FileStorageProperties(val uploadDir: String)
