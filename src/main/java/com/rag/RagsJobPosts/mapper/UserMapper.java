package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.dto.UserRegisterResponseDTO;
import com.rag.RagsJobPosts.models.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(source = "username", target = "username")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "roles", target = "roles")
  UserRegisterResponseDTO entityToResponseDTO(UserEntity entity);
}
