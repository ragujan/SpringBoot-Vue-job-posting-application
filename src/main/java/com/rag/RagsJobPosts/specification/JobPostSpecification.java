package com.rag.RagsJobPosts.specification;

import com.rag.RagsJobPosts.models.JobPost;
import com.rag.RagsJobPosts.models.TechStack;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import javax.script.ScriptEngine;
import java.util.List;


public class JobPostSpecification {

    public static Specification<JobPost> filterBy(JobFilterCriteria filterDTO) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (filterDTO.getCompany() != null ) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("company"),filterDTO.getCompany()));
            }
            if (filterDTO.getDescription() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + filterDTO.getDescription().toLowerCase() + "%"));
            }
            if (filterDTO.getJobPosters() != null) {
                predicate = criteriaBuilder.and(predicate, root.get("jobPoster").in( filterDTO.getJobPosters()));
            }
            if (filterDTO.getTitle() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + filterDTO.getTitle().toLowerCase() + "%"));
            }
            if (filterDTO.getCreatedAtStart() != null && filterDTO.getCreatedAtEnd()!=null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("createdAt"),  filterDTO.getCreatedAtStart() ,filterDTO.getCreatedAtEnd()));
            }
            if (filterDTO.getJobStatus() != null ) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("jobStatus"),  filterDTO.getJobStatus() ));
            }
            if (filterDTO.getExpLevel() != null ) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("expLevel"),  filterDTO.getExpLevel() ));
            }
            if(filterDTO.getTechStack()!=null && !filterDTO.getTechStack().isEmpty()){
               Join<JobPost,TechStack> join = root.join("techStacks");
               List<Long> techStackIds = filterDTO.getTechStack().stream().map(TechStack::getId).toList();
               predicate = criteriaBuilder.and(predicate, join.get("id").in(techStackIds));
            }


            return predicate;
        };
    }
}
