package com.rag.RagsJobPosts.models.dto;

import com.rag.RagsJobPosts.models.enums.ExpLevel;
import com.rag.RagsJobPosts.models.enums.TechCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostFilterDTO {
    private Long id;
    private String CompanyName;
    private String title;
    private String description;
    private String techCategory;
    private ExpLevel expLevel;
    private Integer hoursPostedAfter;
    private Integer daysPostedAfter;
    private Integer weeksPostedAfter;
    private Boolean isAllSearch;
    private String value;
}
