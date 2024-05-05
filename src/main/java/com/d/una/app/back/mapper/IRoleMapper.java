package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper extends IEntityMapper<RoleDto, Role>{
    Role toDomain(RoleDto dto);
    RoleDto toDto(Role entity);
}