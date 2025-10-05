package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.exceptions.ResourceNotFoundException;
import com.rag.RagsJobPosts.mapper.TechStackMapper;
import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.dto.CreateTechStackDTO;
import com.rag.RagsJobPosts.models.enums.TechCategory;
import com.rag.RagsJobPosts.repository.TechStackRepository;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class TechStackServiceImpl implements TechStackService {

    private final TechStackRepository techStackRepository;
    private final TechStackMapper mapper;

    //    private final CategoryRepository
    @Override
    public List<TechStack> getAllTechStacks() {
        return techStackRepository.findAll();
    }

    @Override
    public TechStack createTechStack(CreateTechStackDTO stackDTO) {

        TechStack techStack = mapper.createDTOToEntity(stackDTO);
        techStack.setTechCategory(stackDTO.getTechCategory());
        return techStackRepository.save(techStack);
    }
    @Override
    public List<TechStack> createTechStacks(List<CreateTechStackDTO> stackDTOs) {
        List<TechStack> techStacks = stackDTOs.stream()
                .map(dto -> {
                    TechStack techStack = mapper.createDTOToEntity(dto);
                    techStack.setTechCategory(dto.getTechCategory());
                    return techStack;
                })
                .collect(Collectors.toList());
        return techStackRepository.saveAll(techStacks);
    }
}
