package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.UserDto;
import com.d.una.app.back.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper extends IEntityMapper<UserDto, User>{
    User toDomain(UserDto dto);
    UserDto toDto(User entity);
}
