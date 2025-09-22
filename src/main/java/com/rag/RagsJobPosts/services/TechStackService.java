package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.dto.CreateTechStackDTO;

import java.util.List;

public interface TechStackService {
    List<TechStack> getAllTechStacks();
    TechStack createTechStack(CreateTechStackDTO stackDTO);
    public List<TechStack> createTechStacks(List<CreateTechStackDTO> stackDTOs);
}
