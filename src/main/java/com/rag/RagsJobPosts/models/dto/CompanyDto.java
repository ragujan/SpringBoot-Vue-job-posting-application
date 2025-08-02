package com.rag.RagsJobPosts.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private String description;
    private String website;
    private String industry;
    private String logoUrl;
    private String businessEmail;
    private boolean isVerified;
}
