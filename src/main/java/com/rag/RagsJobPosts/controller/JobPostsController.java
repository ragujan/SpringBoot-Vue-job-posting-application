package com.rag.RagsJobPosts.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/job-posts")
@RestController
@PreAuthorize("hasRole('JOB_POSTER')")
@EnableMethodSecurity(prePostEnabled = true)
public class JobPostsController {

    @GetMapping("/jobs")
    public String loginView() {
        return "jobs ";
    }
}