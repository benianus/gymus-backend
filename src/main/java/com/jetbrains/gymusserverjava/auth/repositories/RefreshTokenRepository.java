package com.jetbrains.gymusserverjava.auth.repositories;

import com.jetbrains.gymusserverjava.auth.entities.RefreshToken;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(@NotNull String refreshToken);

}
