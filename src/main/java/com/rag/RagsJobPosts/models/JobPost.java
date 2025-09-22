package com.rag.RagsJobPosts.models;

import com.rag.RagsJobPosts.models.enums.ExpLevel;
import com.rag.RagsJobPosts.models.enums.JobStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "job_post")
public class JobPost extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "job_poster_id")
    private JobPoster jobPoster;

    private String title;
    private String description;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<TechStack> techStack;

    @ManyToMany
    @JoinTable(
            name = "job_post_tech_stack",
            joinColumns = @JoinColumn(name = "job_post_id"),
            inverseJoinColumns = @JoinColumn(name = "tech_stack_id")
    )
    private List<TechStack> techStacks;

    @Enumerated(EnumType.STRING)
    private ExpLevel expLevel;

    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

}
