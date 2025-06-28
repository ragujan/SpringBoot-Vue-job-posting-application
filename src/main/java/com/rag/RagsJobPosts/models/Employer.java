package com.rag.RagsJobPosts.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employer")
public class Employer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id" )
    private UserEntity user;

    @Column(name = "verified_by_company")
    private Boolean verifiedByCompany;


}
