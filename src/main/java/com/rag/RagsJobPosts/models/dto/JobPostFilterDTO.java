package com.rag.RagsJobPosts.models.dto;

import com.rag.RagsJobPosts.models.enums.ExpLevel;
import com.rag.RagsJobPosts.models.enums.TechCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostFilterDTO {
    private String CompanyName;
    private String jobPosterName;
    private String title;
    private String description;
    private TechCategory techCategory;
    private List<Long> techStacks;
    private ExpLevel expLevel;
    private Integer hoursPostedAfter;
    private Integer daysPostedAfter;
    private Integer weeksPostedAfter;
    private Boolean isAllSearch;
    private String value;
}
