package com.rag.RagsJobPosts.models.dto;

import com.rag.RagsJobPosts.models.enums.TechCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTechStackDTO
{

    private String name;
    private TechCategory techCategory;
}
