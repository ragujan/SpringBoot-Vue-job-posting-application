package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.dto.CompanyDto;
import com.rag.RagsJobPosts.models.dto.CreateCompanyDto;
import com.rag.RagsJobPosts.repository.CompanyRepository;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CreateCompanyDto dto);
    CompanyDto toDto(Company company);
    List<CompanyDto> toDtoList(List<Company> companies);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompanyFromDto(CreateCompanyDto dto, @MappingTarget Company company);
}
