package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.exceptions.ResourceNotFoundException;
import com.rag.RagsJobPosts.mapper.JobPostMapper;
import com.rag.RagsJobPosts.mapper.JobPosterMapper;
import com.rag.RagsJobPosts.models.*;
import com.rag.RagsJobPosts.models.dto.JobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostFilterDTO;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import com.rag.RagsJobPosts.models.enums.Roles;
import com.rag.RagsJobPosts.repository.JobPostRepository;
import com.rag.RagsJobPosts.repository.JobPosterRepository;
import com.rag.RagsJobPosts.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class JobPosterServiceImpl implements JobPosterService {

    private final JobPosterRepository repository;
    private final UserRepository userRepository;
    private final JobPosterMapper mapper;
    private final JobPostMapper jobPostMapper;
    private final JobPosterRepository jobPosterRepository;

    private final JobPostRepository jobPostRepository;

    @Override
    public Page<JobPosterDTO> getAllJobPosters(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::entityToDTO);
    }

    @Override
    public JobPosterDTO findById(Long userId) {
        return null;
    }

    @Override
    public JobPosterDTO findByEmail(String email) {
        return null;
    }

    @Override
    public JobPosterDTO findByUserName(String username) {
        return null;
    }

    @Override
    public JobPosterDTO deleteJobPoster(Long userId) {
        return null;
    }

    @Override
    public Page<JobPostDto> filterJobPosts(JobPostFilterDTO filter) {
        return null;
    }

    @Override
    public Company getCompanyOfJobPoster(String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> {
            log.error("username not found {}", username);
            return new ResourceNotFoundException("username not found " + username);
        });

        JobPoster jobPoster = jobPosterRepository.findByUser(userEntity).orElseThrow(() -> {
            log.error("user is not a job poster {}", username);
            return new ResourceNotFoundException("user is not a job poster " + username);
        });
        if (jobPoster.getCompany() == null) {
            throw new ResourceNotFoundException("company is not found for this user " + username);
        } else {
            return jobPoster.getCompany();
        }
    }

    @Override
    public Page<JobPostDto> getJobPostsOfJobPoster(String username , Pageable pageable) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> {
            log.error("username not found {}", username);
            return new ResourceNotFoundException("username not found " + username);
        });

        JobPoster jobPoster = jobPosterRepository.findByUser(userEntity).orElseThrow(() -> {
            log.error("user is not a job poster {}", username);
            return new ResourceNotFoundException("user is not a job poster " + username);
        });
       List<JobPost> jobPosts = jobPostRepository.findByJobPoster(jobPoster);
       List<JobPostDto> jobPostDtos = jobPosts.stream().map(jobPostMapper::entityToDTO).toList();
       return new PageImpl<>(jobPostDtos,pageable,jobPostDtos.size());
    }
}
