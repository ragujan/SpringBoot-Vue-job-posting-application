package com.rag.RagsJobPosts.specification;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.enums.ExpLevel;
import com.rag.RagsJobPosts.models.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobFilterCriteria {
    private Long id;
    private Company company;
    private JobPoster jobPoster;
    private String title;
    private String description;
    private List<TechStack> techStack;
    private ExpLevel expLevel;
    private JobStatus jobStatus;
    private LocalDateTime createdAtStart;
    private LocalDateTime createdAtEnd;
    private LocalDateTime updatedAt;
}
