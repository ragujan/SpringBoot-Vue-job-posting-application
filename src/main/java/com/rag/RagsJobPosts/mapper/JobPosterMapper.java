package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.dto.JobPosterRegisterResponseDTO;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.models.dto.JobPosterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CompanyMapper.class, UserMapper.class})
public interface JobPosterMapper {

    @Mapping(source = "user",target = "userRegisterResponseDTO")
    JobPosterRegisterResponseDTO entityToRegisterResponseDTO(JobPoster jobPoster);

    @Mapping(source = "user",target = "userEntityDTO")
    @Mapping(source = "id",target = "id")
    @Mapping(source = "company",target = "companyDTO")
    @Mapping(source = "verifiedByCompany", target = "verifiedByCompany")
    JobPosterDTO entityToDTO(JobPoster jobPoster);
}
