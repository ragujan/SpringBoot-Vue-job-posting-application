package com.rag.RagsJobPosts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterEmployerDto {
    private String email;
    private String password;
    private String username;
    private Long companyId;
}
