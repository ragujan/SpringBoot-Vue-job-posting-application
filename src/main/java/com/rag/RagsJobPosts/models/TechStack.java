package com.rag.RagsJobPosts.models;

import com.rag.RagsJobPosts.models.enums.TechCategory;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tech_stack")
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TechCategory techCategory;
}
