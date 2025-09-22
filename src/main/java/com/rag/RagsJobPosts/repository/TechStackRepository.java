package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.TechStack;
import com.rag.RagsJobPosts.models.enums.TechCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechStackRepository extends JpaRepository<TechStack,Long> {
    List<TechStack> findByNameContainingIgnoreCase(String name);
    List<TechStack> findByTechCategory(TechCategory techCategory);
}
