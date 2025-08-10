package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.dto.JobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostFilter;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobPosterService {
    public Page<JobPosterDTO> getAllJobPosters(Pageable pageable);
    public JobPosterDTO findById(Long userId);
    public JobPosterDTO findByEmail(String email);
    public JobPosterDTO findByUserName(String username);
    public JobPosterDTO deleteJobPoster(Long userId);
    public Page<JobPostDto> filterJobPosts(JobPostFilter filter);
    public Company getCompanyOfJobPoster(String username);
}
