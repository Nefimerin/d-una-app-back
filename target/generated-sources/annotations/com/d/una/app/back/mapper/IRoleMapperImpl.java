package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.model.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T21:54:20-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class IRoleMapperImpl implements IRoleMapper {

    @Override
    public Role toDomain(RoleDto dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( dto.getRoleId() );
        role.setName( dto.getName() );
        role.setDescription( dto.getDescription() );

        return role;
    }

    @Override
    public RoleDto toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( entity.getRoleId() );
        roleDto.setName( entity.getName() );
        roleDto.setDescription( entity.getDescription() );

        return roleDto;
    }
}
