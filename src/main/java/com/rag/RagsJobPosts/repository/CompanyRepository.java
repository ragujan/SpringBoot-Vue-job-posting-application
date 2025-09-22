package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByBusinessEmail(String businessEmail);
    boolean existsByBusinessEmail(String businessEmail);
    boolean existsByName(String name);
    Optional<Company> findByNameIgnoreCase(String name);
}
