package com.jetbrains.gymusserverjava.auth.entities;

import java.time.LocalDateTime;

public class RefreshToken {
    private String refreshToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
