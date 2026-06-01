package com.jetbrains.shared.FileStorage

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "file")
data class FileStorageProperties(val uploadDir: String)
