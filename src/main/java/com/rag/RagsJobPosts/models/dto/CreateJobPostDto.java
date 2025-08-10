package com.rag.RagsJobPosts.models.dto;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.models.enums.ExpLevel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobPostDto {
    private Long jobPosterId;
    private String title;
    private String description;
    private List<Long> techStackIds;
    private ExpLevel expLevel;

}
