package com.example.productcatalog.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SuggestionMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SuggestionSession session;

    private String role;

    @Column(columnDefinition = "TEXT")
    private String content;
}