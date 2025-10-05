package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.exceptions.ResourceNotFoundException;
import com.rag.RagsJobPosts.mapper.CompanyMapper;
import com.rag.RagsJobPosts.mapper.JobFilterMapper;
import com.rag.RagsJobPosts.mapper.JobPostMapper;
import com.rag.RagsJobPosts.mapper.JobPosterMapper;
import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPost;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.dto.*;
import com.rag.RagsJobPosts.models.enums.JobStatus;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final JobFilterMapper jobFilterMapper;


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
        jobPost.setTechStacks(techStack);
        jobPost.setJobStatus(JobStatus.OPEN);
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
        JobFilterCriteria jobFilterCriteria = jobFilterMapper.dtoToCriteria(filter);
        List<TechStack> techStacks = new ArrayList<>();
        if(filter.getTechCategory()!=null ){
            techStacks.addAll(techStackRepository.findByTechCategory(filter.getTechCategory()));
        }
        if (filter.getTechStacks() != null && !filter.getTechStacks().isEmpty()) {
            techStacks.addAll(techStackRepository.findAllById(filter.getTechStacks()));
        }
        jobFilterCriteria.setTechStack(techStacks);
        LocalDateTime postedAfter = null;
        if (filter.getDaysPostedAfter() != null) {
            postedAfter = LocalDateTime.now().minusDays(filter.getDaysPostedAfter());
            jobFilterCriteria.setCreatedAtStart(postedAfter);
            jobFilterCriteria.setCreatedAtEnd(LocalDateTime.now());
        } else if (filter.getHoursPostedAfter() != null) {
            postedAfter = LocalDateTime.now().minusHours(filter.getHoursPostedAfter());
            jobFilterCriteria.setCreatedAtStart(postedAfter);
            jobFilterCriteria.setCreatedAtEnd(LocalDateTime.now());
        } else if (filter.getWeeksPostedAfter() != null) {
            postedAfter = LocalDateTime.now().minusWeeks(filter.getWeeksPostedAfter());
            jobFilterCriteria.setCreatedAtStart(postedAfter);
            jobFilterCriteria.setCreatedAtEnd(LocalDateTime.now());
        }

        if(filter.getExpLevel()!=null){
            jobFilterCriteria.setExpLevel(filter.getExpLevel());
        }

        if (filter.getCompanyName() != null) {
            Optional<Company> companyOptional = companyRepository.findByNameIgnoreCase(filter.getCompanyName());
            companyOptional.ifPresent(jobFilterCriteria::setCompany);
        }

        if(filter.getExpLevel()!=null){
            jobFilterCriteria.setExpLevel(filter.getExpLevel());
        }
        // --- String Filters ---
        if (filter.getJobPosterName() != null && !filter.getJobPosterName().isBlank()) {
            List<JobPoster> jobPosterOptional = jobPosterRepository.findByUser_UsernameIgnoreCase(filter.getJobPosterName());
            jobFilterCriteria.setJobPosters(jobPosterOptional);
        }

        if (filter.getTitle() != null && !filter.getTitle().isBlank()) {
            jobFilterCriteria.setTitle(filter.getTitle());
        }

        if (filter.getDescription() != null && !filter.getDescription().isBlank()) {
            jobFilterCriteria.setDescription(filter.getDescription());
        }


//        if(filter)
        Specification<JobPost> specification = JobPostSpecification.filterBy(jobFilterCriteria);
        List<JobPost> jobPosts = repository.findAll(specification);
        List<JobPostDto> jobPostDtos = jobPosts.stream().map(jobPostMapper::entityToDTO).toList();
        return new PageImpl<>(jobPostDtos, pageable, jobPostDtos.size());
    }


}
