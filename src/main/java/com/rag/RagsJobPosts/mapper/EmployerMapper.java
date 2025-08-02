package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.dto.EmployerRegisterResponseDTO;
import com.rag.RagsJobPosts.models.JobPoster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface EmployerMapper {

    @Mapping(source = "user",target = "userRegisterResponseDTO")
    EmployerRegisterResponseDTO entityToRegisterResponseDTO(JobPoster jobPoster);
}
