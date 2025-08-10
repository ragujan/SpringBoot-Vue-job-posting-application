package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechStackRepository extends JpaRepository<TechStack,Long> {
    List<TechStack> findByNameContainingIgnoreCase(String name);
}
