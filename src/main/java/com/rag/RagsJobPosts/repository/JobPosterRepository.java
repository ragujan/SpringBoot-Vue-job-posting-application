package com.rag.RagsJobPosts.repository;

import com.rag.RagsJobPosts.models.Company;
import com.rag.RagsJobPosts.models.JobPoster;
import com.rag.RagsJobPosts.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPosterRepository extends JpaRepository<JobPoster,Long> {
    List<JobPoster> findByCompany(Company company);
    Optional<JobPoster> findByUser(UserEntity userEntity);
    List<JobPoster> findByUserIn(List<UserEntity> ids);

    List<JobPoster> findByUser_UsernameIgnoreCase(String username);
    List<JobPoster> findByUser_Username(String username);

}
