package com.example.productcatalog.repository;

import com.example.productcatalog.model.SuggestionMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestionMessageRepository
        extends JpaRepository<SuggestionMessage, Long> {
}