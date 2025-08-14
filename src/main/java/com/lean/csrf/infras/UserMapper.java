package com.lean.csrf.infras;

import com.lean.csrf.view.User;
import com.lean.csrf.view.dto.UserRequestDto;
import com.lean.csrf.view.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
     UserResponseDto toResponseDTO(User entity);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto dto);
}
