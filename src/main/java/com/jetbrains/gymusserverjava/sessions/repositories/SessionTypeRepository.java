package com.jetbrains.gymusserverjava.sessions.repositories;

import com.jetbrains.gymusserverjava.sessions.entities.SessionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionTypeRepository extends JpaRepository<SessionType, Integer> {

    Optional<SessionType> findOneByName(String sessionTypeName);

}
