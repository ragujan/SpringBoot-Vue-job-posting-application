package com.rag.RagsJobPosts.models.dto;

import com.rag.RagsJobPosts.models.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostDto {
    private Long id;
    private JobPosterDTO jobPosterDTO;
    private String title;
    private String description;
    private JobStatus jobStatus;
}
