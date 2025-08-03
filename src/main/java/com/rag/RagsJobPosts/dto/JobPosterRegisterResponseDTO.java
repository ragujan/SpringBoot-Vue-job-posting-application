package com.rag.RagsJobPosts.dto;

import com.rag.RagsJobPosts.models.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPosterRegisterResponseDTO {
    private Long id;
    private Company company;
    private UserRegisterResponseDTO userRegisterResponseDTO;
    private Boolean verifiedByCompany;
}
