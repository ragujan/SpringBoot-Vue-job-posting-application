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
            if (filterDTO.getCompany() != null && !filterDTO.getCompany().isEmpty()) {
                predicate = criteriaBuilder.and(predicate, root.get("company").in(filterDTO.getCompany()));
            }
            if (filterDTO.getDescription() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("description"), "%" + filterDTO.getDescription() + "%"));
            }
            if (filterDTO.getTitle() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + filterDTO.getTitle() + "%"));
            }
            if(filterDTO.getTechStack()!=null && !filterDTO.getTechStack().isEmpty()){
               Join<JobPost,TechStack> join = root.join("techStack");
               List<Long> techStackIds = filterDTO.getTechStack().stream().map(TechStack::getId).toList();
               predicate = criteriaBuilder.and(predicate, join.get("id").in(techStackIds));
            }


            return predicate;
        };
    }
}
