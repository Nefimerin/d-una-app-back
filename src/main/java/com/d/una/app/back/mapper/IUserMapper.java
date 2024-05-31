package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.UserRequestDto;
import com.d.una.app.back.domain.UserResponseDto;
import com.d.una.app.back.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper extends IEntityMapper<UserRequestDto, User>{
    User toDomain(UserRequestDto dto);
    UserRequestDto toDto(User entity);
    UserResponseDto toDtoResponse(User entity);

}
