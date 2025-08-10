package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPost;
import com.rag.RagsJobPosts.models.JobPoster;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface JobPostRepository extends JpaRepository<JobPost,Long>, JpaSpecificationExecutor<JobPost> {
    List<JobPost> findByCompany(Company company);
    Optional<JobPost> findByJobPoster(JobPoster jobPoster);
}
