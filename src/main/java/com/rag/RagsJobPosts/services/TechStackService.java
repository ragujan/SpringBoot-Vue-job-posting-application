package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.dto.CreateTechStackDTO;

import java.util.List;

public interface TechStackService {
    List<TechStack> getAllTechStacks();
    TechStack createTechStack(CreateTechStackDTO stackDTO);
}
