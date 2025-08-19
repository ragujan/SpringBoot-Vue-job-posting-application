package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.dto.CreateTechStackDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechStackMapper {

    TechStack createDTOToEntity(CreateTechStackDTO createDTO);
}
