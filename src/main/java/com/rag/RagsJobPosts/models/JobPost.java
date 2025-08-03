package com.rag.RagsJobPosts.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "job_post")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "job_poster_id")
    private JobPoster jobPoster;

    private String title;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
