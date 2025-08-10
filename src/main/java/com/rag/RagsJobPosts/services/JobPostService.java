package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.models.dto.CreateJobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostFilterDTO;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobPostService {

    JobPostDto createJobPost(CreateJobPostDto createJobPostDto);
    Page<JobPostDto> listAllJobPosts(Pageable pageable);
    JobPostDto findById(Long id);
    Page<JobPostDto> listAllJobPostsByCompany(Pageable pageable, Long companyId);
    List<JobPostDto> filterJobPosts(JobPostFilterDTO filter);

}
