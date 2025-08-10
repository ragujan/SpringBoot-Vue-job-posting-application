package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.exceptions.ResourceNotFoundException;
import com.rag.RagsJobPosts.mapper.CompanyMapper;
import com.rag.RagsJobPosts.mapper.JobPostMapper;
import com.rag.RagsJobPosts.mapper.JobPosterMapper;
import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPost;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.dto.*;
import com.rag.RagsJobPosts.repository.CompanyRepository;
import com.rag.RagsJobPosts.repository.JobPostRepository;
import com.rag.RagsJobPosts.repository.JobPosterRepository;
import com.rag.RagsJobPosts.repository.TechStackRepository;
import com.rag.RagsJobPosts.specification.JobFilterCriteria;
import com.rag.RagsJobPosts.specification.JobPostSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository repository;
    private final CompanyRepository companyRepository;
    private final JobPosterRepository jobPosterRepository;
    private final JobPostMapper jobPostMapper;
    private final CompanyMapper companyMapper;
    private final JobPosterMapper jobPosterMapper;
    private final TechStackRepository techStackRepository;

    @Override
    public JobPostDto createJobPost(CreateJobPostDto createJobPostDto) {
        Long jobPosterId = createJobPostDto.getJobPosterId();

        var jobPoster = jobPosterRepository.findById(jobPosterId)
                .orElseThrow(() -> {
                    log.error("JobPoster with id {} not found", jobPosterId);
                    return new ResourceNotFoundException("JobPoster not found with id: " + jobPosterId);
                });
        List<TechStack> techStack = techStackRepository.findAllById(createJobPostDto.getTechStackIds());
        JobPost jobPost = jobPostMapper.createDTOTOEntity(createJobPostDto);
        jobPost.setJobPoster(jobPoster);
        jobPost.setCompany(jobPoster.getCompany());
        jobPost.setTechStack(techStack);
        return jobPostMapper.entityToDTO(repository.save(jobPost));
    }

    @Override
    public Page<JobPostDto> listAllJobPosts(Pageable pageable) {
        Page<JobPost> jobPosts = repository.findAll(pageable);
        List<JobPostDto> jobPostDtoList = jobPosts.stream().map(jobPostMapper::entityToDTO).toList();
        return new PageImpl<>(jobPostDtoList, pageable, jobPostDtoList.size());
    }

    @Override
    public JobPostDto findById(Long id) {
        return null;
    }

    @Override
    public Page<JobPostDto> listAllJobPostsByCompany(Pageable pageable, Long companyId) {

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> {
                    log.error("company not found for id {}", companyId);
                    return new ResourceNotFoundException("company not found for id " + companyId);

                }
        );
        List<JobPostDto> jobPosts = repository.findByCompany(company).stream().map(jobPostMapper::entityToDTO).toList();
        return new PageImpl<>(jobPosts, pageable, jobPosts.size());
    }

    @Override
    public Page<JobPostDto> filterJobPosts(JobPostFilterDTO filter, Pageable pageable) {
        JobFilterCriteria jobFilterCriteria = new JobFilterCriteria();
        if (!filter.getTechCategory().isEmpty()) {
            List<TechStack> techStacks = techStackRepository.findByNameContainingIgnoreCase(filter.getTechCategory());
        }
        if (filter.getTechStacks() != null && !filter.getTechStacks().isEmpty()) {
            List<TechStack> techStacks = techStackRepository.findAllById(filter.getTechStacks());
            jobFilterCriteria.setTechStack(techStacks);
        }
        Specification<JobPost> specification = JobPostSpecification.filterBy(jobFilterCriteria);
        List<JobPost> jobPosts = repository.findAll(specification);
        List<JobPostDto> jobPostDtos  = jobPosts.stream().map(jobPostMapper::entityToDTO).toList();
        return new PageImpl<>(jobPostDtos, pageable, jobPostDtos.size());
    }


}
