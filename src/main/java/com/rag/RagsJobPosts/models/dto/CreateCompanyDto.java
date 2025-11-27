package com.rag.RagsJobPosts.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyDto {

    private String name;
    private String description;
    private String website;
    private String industry;
    private String logoUrl;
    private String businessEmail;
    private Boolean isVerified;
}
