package com.rag.RagsJobPosts.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/job-posts")
@RestController
@PreAuthorize("hasRole('ADMIN')")
@EnableMethodSecurity(prePostEnabled = true)
public class JobPostsController {

    @GetMapping("/jobs")
    public String loginView() {
        return "jobs ";
    }
}