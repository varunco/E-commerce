package com.example.productcatalog.repository;

import com.example.productcatalog.model.SuggestionSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuggestionSessionRepository
        extends JpaRepository<SuggestionSession, Long> {

    Optional<SuggestionSession> findByIdAndUserId(
            Long sessionId,
            Long userId
    );
}