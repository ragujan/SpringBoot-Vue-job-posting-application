package com.rag.RagsJobPosts.controller;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.dto.CreateJobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostFilterDTO;
import com.rag.RagsJobPosts.services.JobPostService;
import com.rag.RagsJobPosts.services.JobPosterService;
import com.rag.RagsJobPosts.services.JwtService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/job-posts")
@RestController
@PreAuthorize("hasRole('JOB_POSTER')")
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class JobPostsController {
    private final JobPostService jobPostService;
    private final JwtService jwtService;
    private final JobPosterService jobPosterService;

    @GetMapping("/created-by/job-poster")
    public ResponseEntity<Page<JobPostDto>> getAllJobPosts(@RequestHeader(name = "Authorization",required = false) @Parameter(hidden = true) String authToken, Pageable pageable){
        String username = jwtService.getUsernameFromToken(authToken.substring(7));
        Company company = jobPosterService.getCompanyOfJobPoster(username);
        return ResponseEntity.ok(jobPostService.listAllJobPostsByCompany(pageable,company.getId()));
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<JobPostDto>> filterJobPosts(JobPostFilterDTO jobPostFilter){
        return null;
    }


    @PostMapping("/create")
    public ResponseEntity<JobPostDto> createJobPost(@RequestBody CreateJobPostDto createDTO){
        return ResponseEntity.ok(jobPostService.createJobPost(createDTO));
    }

}