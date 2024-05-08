package com.d.una.app.back.mapper;

import com.d.una.app.back.domain.UserDto;
import com.d.una.app.back.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-07T21:55:18-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User toDomain(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setAddress( dto.getAddress() );

        return user;
    }

    @Override
    public UserDto toDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( entity.getId() );
        userDto.setFirstName( entity.getFirstName() );
        userDto.setLastName( entity.getLastName() );
        userDto.setEmail( entity.getEmail() );
        userDto.setPassword( entity.getPassword() );
        userDto.setAddress( entity.getAddress() );

        return userDto;
    }
}
