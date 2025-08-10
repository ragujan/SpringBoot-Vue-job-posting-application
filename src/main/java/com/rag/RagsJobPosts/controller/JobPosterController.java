package com.rag.RagsJobPosts.controller;

import com.rag.RagsJobPosts.models.dto.CompanyDto;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import com.rag.RagsJobPosts.services.JobPosterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/job-posters")
@RestController
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class JobPosterController {

    private final JobPosterService service;

    @GetMapping
    public ResponseEntity<Page<JobPosterDTO>> getAllJobPosters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllJobPosters(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Page<JobPosterDTO>> deleteJobPoster(
            @PathVariable Long id
    ) {

        return null;
    }


}
