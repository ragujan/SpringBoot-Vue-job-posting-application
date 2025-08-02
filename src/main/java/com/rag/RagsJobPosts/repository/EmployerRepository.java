package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPoster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployerRepository extends JpaRepository<JobPoster,Long> {
    List<JobPoster> findByCompany(Company company);
}
