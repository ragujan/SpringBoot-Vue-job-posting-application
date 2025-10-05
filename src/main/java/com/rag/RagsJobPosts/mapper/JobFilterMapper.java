package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.models.dto.JobPostFilterDTO;
import com.rag.RagsJobPosts.specification.JobFilterCriteria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobFilterMapper {
    JobFilterCriteria dtoToCriteria(JobPostFilterDTO filterDTO);



}
