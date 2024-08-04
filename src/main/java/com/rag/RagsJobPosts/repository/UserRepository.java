package com.rag.RagsJobPosts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.rag.RagsJobPosts.models.UserEntity;

@Repository
//public interface  UserRepository  extends JpaRepository<UserEntity, Long> {
public interface  UserRepository  extends CrudRepository<UserEntity, Long> {
   Optional<UserEntity> findByUsername(String username);
   Optional<UserEntity> findByEmail(String email);
}
