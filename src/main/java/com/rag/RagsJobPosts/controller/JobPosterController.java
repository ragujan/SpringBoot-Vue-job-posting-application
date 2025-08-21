package com.rag.RagsJobPosts.controller;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.dto.CompanyDto;
import com.rag.RagsJobPosts.models.dto.JobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import com.rag.RagsJobPosts.services.JobPosterService;
import com.rag.RagsJobPosts.services.JwtService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/job-posters")
@RestController
@PreAuthorize("hasRole('JOB_POSTER')")
@AllArgsConstructor
public class JobPosterController {

    private final JobPosterService service;
    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<Page<JobPosterDTO>> getAllJobPosters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllJobPosters(pageable));
    }
    @GetMapping("/job-posts")
    public ResponseEntity<Page<JobPostDto>> getJobsByJobPoster(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(name = "Authorization", required = false) @Parameter(hidden = true) String authToken
    ) {
        Pageable pageable = PageRequest.of(page, size);
        String username = jwtService.getUsernameFromToken(authToken.substring(7));
        return ResponseEntity.ok(service.getJobPostsOfJobPoster(username,pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Page<JobPosterDTO>> deleteJobPoster(
            @PathVariable Long id
    ) {

        return null;
    }


}
