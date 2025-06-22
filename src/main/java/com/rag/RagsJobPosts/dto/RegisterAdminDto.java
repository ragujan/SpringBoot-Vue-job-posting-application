package com.rag.RagsJobPosts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterAdminDto {
    private String email;
    private String password;
    private String username;
}
