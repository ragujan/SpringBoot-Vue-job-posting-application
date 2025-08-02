package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.exceptions.DeletionFailedException;
import com.rag.RagsJobPosts.exceptions.ResourceNotFoundException;
import com.rag.RagsJobPosts.mapper.CompanyMapper;
import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.dto.CompanyDto;
import com.rag.RagsJobPosts.models.dto.CreateCompanyDto;
import com.rag.RagsJobPosts.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;


    @Override
    public CompanyDto createCompany(CreateCompanyDto dto) {
        if (companyRepository.existsByBusinessEmail(dto.getBusinessEmail())) {
            throw new IllegalArgumentException("Business email already exists");
        }
        if (companyRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Company name already exists");
        }
        Company company = companyMapper.toEntity(dto);
        company.setVerified(false);
        Company saved = companyRepository.save(company);
        return companyMapper.toDto(saved);
    }

    @Override
    public CompanyDto updateCompany(Long id, CreateCompanyDto dto) {
        Company existing = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        if (!existing.getBusinessEmail().equals(dto.getBusinessEmail()) &&
                companyRepository.existsByBusinessEmail(dto.getBusinessEmail())) {
            throw new IllegalArgumentException("Business email already exists");
        }

        if (!existing.getName().equals(dto.getName()) &&
                companyRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Company name already exists");
        }

        companyMapper.updateCompanyFromDto(dto, existing);
        Company updated = companyRepository.save(existing);
        return companyMapper.toDto(updated);
    }

    @Override
    public void deleteCompany(Long id) {
        try {
            if (!companyRepository.existsById(id)) {
                log.error("Company not found for id {}", id);
                throw new ResourceNotFoundException("Company not found for id " + id);
            }
            companyRepository.deleteById(id);
        } catch (Exception ex) {
            log.error("deletion failed ");
            throw new DeletionFailedException("Deletion failed " + ex.getMessage());
        }

    }

    @Override
    public Page<CompanyDto> getAllCompanies(Pageable pageable) {
        Page<Company> page = companyRepository.findAll(pageable);
        return new PageImpl<>(
                page.getContent().stream()
                        .map(companyMapper::toDto)
                        .collect(Collectors.toList()),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
        return companyMapper.toDto(company);
    }
}
