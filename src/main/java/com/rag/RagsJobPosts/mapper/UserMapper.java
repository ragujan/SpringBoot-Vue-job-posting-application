package com.rag.RagsJobPosts.mapper;

import com.rag.RagsJobPosts.dto.UserRegisterResponseDTO;
import com.rag.RagsJobPosts.models.UserEntity;
import com.rag.RagsJobPosts.models.dto.UserEntityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(source = "username", target = "username")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "roles", target = "roles")
  UserRegisterResponseDTO entityToResponseDTO(UserEntity entity);

  @Mapping(source = "username", target = "username")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "id", target = "id")
  UserEntityDTO entityToDTO(UserEntity entity);
}
