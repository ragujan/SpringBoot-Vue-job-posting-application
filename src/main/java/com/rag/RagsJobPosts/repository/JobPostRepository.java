package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPost;
import com.rag.RagsJobPosts.models.JobPoster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost,Long> {
    List<JobPost> findByCompany(Company company);
    Optional<JobPost> findByJobPoster(JobPoster jobPoster);
}
