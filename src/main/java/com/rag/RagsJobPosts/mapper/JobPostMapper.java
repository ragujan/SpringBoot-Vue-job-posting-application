package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.models.JobPost;
import com.rag.RagsJobPosts.models.dto.CreateJobPostDto;
import com.rag.RagsJobPosts.models.dto.JobPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {JobPosterMapper.class})
public interface JobPostMapper {

    JobPost createDTOTOEntity(CreateJobPostDto createDTO);

    @Mapping(target = "jobPosterDTO", source = "jobPoster")
    JobPostDto entityToDTO(JobPost entity);
}
