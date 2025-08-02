package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.dto.CompanyDto;
import com.rag.RagsJobPosts.models.dto.CreateCompanyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompanyService {
    CompanyDto createCompany(CreateCompanyDto dto);
    CompanyDto updateCompany(Long id, CreateCompanyDto dto);
    void deleteCompany(Long id);
    Page<CompanyDto> getAllCompanies(Pageable pageable);
    CompanyDto getCompanyById(Long id);
}