package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.dto.JobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostFilterDTO;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobPosterService {
    public Page<JobPosterDTO> getAllJobPosters(Pageable pageable);
    public JobPosterDTO findById(Long userId);
    public JobPosterDTO findByEmail(String email);
    public JobPosterDTO findByUserName(String username);
    public JobPosterDTO deleteJobPoster(Long userId);
    public Page<JobPostDto> filterJobPosts(JobPostFilterDTO filter);
    public Company getCompanyOfJobPoster(String username);
    public Page<JobPostDto> getJobPostsOfJobPoster(String username, Pageable pageable);
}
