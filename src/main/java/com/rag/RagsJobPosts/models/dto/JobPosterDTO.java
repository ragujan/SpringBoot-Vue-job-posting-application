package com.rag.RagsJobPosts.models.dto;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JobPosterDTO {

    private Long id;
    private CompanyDto companyDTO;
    private UserEntityDTO userEntityDTO;
    private Boolean verifiedByCompany;


}
