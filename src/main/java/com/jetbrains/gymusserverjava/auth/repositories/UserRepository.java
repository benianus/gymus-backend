package com.jetbrains.gymusserverjava.auth.repositories;

import com.jetbrains.gymusserverjava.auth.entities.User;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@NonNull @Param("username") String username);

}
