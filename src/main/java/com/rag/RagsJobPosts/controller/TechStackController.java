package com.rag.RagsJobPosts.controller;

import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.dto.CreateTechStackDTO;
import com.rag.RagsJobPosts.services.JobPostService;
import com.rag.RagsJobPosts.services.TechStackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tech-stack")
@RestController
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Slf4j
public class TechStackController {

    private TechStackService techStackService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'JOB_POSTER')")
    public ResponseEntity<List<TechStack>> getAllTechStacks() {
        log.info("get all tech stacks");
        return ResponseEntity.ok(techStackService.getAllTechStacks());
    }

    @PostMapping("/create")
    public ResponseEntity<TechStack> createTechStack(@RequestBody CreateTechStackDTO createDTO) {
        log.info("creating tech stack");
        return ResponseEntity.ok(techStackService.createTechStack(createDTO));
    }

    @PostMapping("/create/batch")
    public ResponseEntity<List<TechStack>> createTechStacks(@RequestBody List<CreateTechStackDTO> createDTOs) {
        log.info("Creating multiple tech stacks, count: {}", createDTOs.size());
        List<TechStack> createdStacks = techStackService.createTechStacks(createDTOs);
        return ResponseEntity.ok(createdStacks);
    }

}
