package com.rag.RagsJobPosts.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseDTO {
    private String username;
    private String email;
    private List<String> roles = new ArrayList<>();
}
