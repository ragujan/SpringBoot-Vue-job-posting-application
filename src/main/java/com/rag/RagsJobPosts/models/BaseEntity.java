package com.rag.RagsJobPosts.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
