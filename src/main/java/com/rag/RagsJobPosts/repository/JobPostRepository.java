package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPost;
import com.rag.RagsJobPosts.models.JobPoster;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Long>, JpaSpecificationExecutor<JobPost> {
    List<JobPost> findByCompany(Company company);
    List<JobPost> findByJobPoster(JobPoster jobPoster);
}
