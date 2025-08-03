package com.rag.RagsJobPosts.services;

import com.rag.RagsJobPosts.mapper.JobPosterMapper;
import com.rag.RagsJobPosts.models.BaseEntity;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import com.rag.RagsJobPosts.models.enums.Roles;
import com.rag.RagsJobPosts.repository.JobPosterRepository;
import com.rag.RagsJobPosts.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class JobPosterServiceImpl implements JobPosterService {

    private final JobPosterRepository repository;
    private final UserRepository userRepository;
    private final JobPosterMapper mapper;

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
}
