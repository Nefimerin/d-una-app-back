package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.domain.UserRequestDto;
import com.d.una.app.back.domain.UserResponseDto;
import com.d.una.app.back.model.Role;
import com.d.una.app.back.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-17T16:44:40-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.4 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User toDomain(UserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setAddress( dto.getAddress() );

        return user;
    }

    @Override
    public UserRequestDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setFirstName( entity.getFirstName() );
        userRequestDto.setLastName( entity.getLastName() );
        userRequestDto.setEmail( entity.getEmail() );
        userRequestDto.setPassword( entity.getPassword() );
        userRequestDto.setAddress( entity.getAddress() );

        return userRequestDto;
    }

    @Override
    public UserResponseDto toDtoResponse(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( entity.getId() );
        userResponseDto.setFirstName( entity.getFirstName() );
        userResponseDto.setLastName( entity.getLastName() );
        userResponseDto.setRoles( roleListToRoleDtoList( entity.getRoles() ) );
        userResponseDto.setEmail( entity.getEmail() );
        userResponseDto.setAddress( entity.getAddress() );

        return userResponseDto;
    }

    protected RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setRoleId( role.getRoleId() );
        roleDto.setName( role.getName() );
        roleDto.setDescription( role.getDescription() );

        return roleDto;
    }

    protected List<RoleDto> roleListToRoleDtoList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RoleDto> list1 = new ArrayList<RoleDto>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRoleDto( role ) );
        }

        return list1;
    }
}
