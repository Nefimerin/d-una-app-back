package com.d.una.app.back.business;

import com.d.una.app.back.config.MessageLoader;
import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.domain.UserRequestDto;
import com.d.una.app.back.domain.UserResponseDto;
import com.d.una.app.back.exception.BusinessRuleException;
import com.d.una.app.back.mapper.IRoleMapper;
import com.d.una.app.back.mapper.IUserMapper;
import com.d.una.app.back.model.Role;
import com.d.una.app.back.model.User;
import com.d.una.app.back.repository.IRoleRepository;
import com.d.una.app.back.repository.IUserRepository;
import com.d.una.app.back.util.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class UserBusinessImpl implements IUserBusiness{
    private static final Logger logger = LoggerFactory.getLogger(UserBusinessImpl.class);
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IRoleMapper roleMapper;
    private final IRoleRepository roleRepository;
    
    @Override
    public ResponseDto<List<UserResponseDto>> findAllUsers() {
        logger.info("Fetching all products");
        List<User> productList = userRepository.findAll();
        List<UserResponseDto> productDtoList = productList.stream()
                .map(userMapper::toDtoResponse)
                .toList();
        return new ResponseDto<>(HttpStatus.OK.value(), MessageLoader.getInstance().getMessage(MessagesConstants.IM001), productDtoList);
    }

    @Override
    public ResponseDto<UserResponseDto> getUserById(Long userId) {
        logger.info("Fetching user by ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, userId)));
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), userMapper.toDtoResponse(user));
    }

    @Override
    public ResponseDto<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        if (this.userRepository.existsByEmail(userRequestDto.getEmail())){
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM010, MessageLoader.getInstance().getMessage(MessagesConstants.EM010, userRequestDto.getEmail()));
        }
        String hashedPassword = BCrypt.hashpw(userRequestDto.getPassword(), BCrypt.gensalt());
        List<Long> roleIds = userRequestDto.getRoleIds();
        List<Role> roles = this.roleRepository.findAllById(roleIds);
        if(roles.isEmpty()) throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, roleIds.toString()));
        User user = userMapper.toDomain(userRequestDto);
        user.setRoles(roles);
        user.setPassword(hashedPassword);
        final var userSaved = userRepository.save(user);
        List<Role> rolesUser = userSaved.getRoles();
        List<RoleDto>  rolesUserDto = rolesUser.stream().map(this.roleMapper::toDto).toList();
        final var userSavedDto = userMapper.toDtoResponse(userSaved);
        userSavedDto.setRoles(rolesUserDto);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM002),userSavedDto );
    }

    @Override
    public ResponseDto<UserResponseDto> updateUser(Long userId, UserRequestDto userRequestDto) {
        logger.info("Updating user with ID: {}", userId);
        List<Long> roleIds = userRequestDto.getRoleIds();
        List<Role> roles = this.roleRepository.findAllById(roleIds);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, userId)));
        final var userOpt = this.userRepository.findByEmail(userRequestDto.getEmail());
        if (userOpt.isPresent() && !userOpt.get().getId().equals(userId))
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM010, MessageLoader.getInstance().getMessage(MessagesConstants.EM010, userRequestDto.getEmail()));
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setAddress(userRequestDto.getAddress());
        user.setRoles(roles);
        String hashedPassword = BCrypt.hashpw(userRequestDto.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        User updatedProduct = userRepository.save(user);
        UserResponseDto updatedUserDto = userMapper.toDtoResponse(updatedProduct);
        List<Role> rolesUser = updatedProduct.getRoles();
        List<RoleDto>  rolesUserDto = rolesUser.stream().map(this.roleMapper::toDto).toList();
        updatedUserDto.setRoles(rolesUserDto);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), updatedUserDto);
    }

    @Override
    public ResponseDto<Void> deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, userId));

        }
        userRepository.deleteById(userId);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001));    }
}
