package com.d.una.app.back.business;

import com.d.una.app.back.config.MessageLoader;
import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.domain.UserDto;
import com.d.una.app.back.exception.BusinessRuleException;
import com.d.una.app.back.mapper.IUserMapper;
import com.d.una.app.back.model.User;
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
    
    @Override
    public ResponseDto<List<UserDto>> findAllUsers() {
        logger.info("Fetching all products");
        List<User> productList = userRepository.findAll();
        List<UserDto> productDtoList = productList.stream()
                .map(userMapper::toDto)
                .toList();
        return new ResponseDto<>(HttpStatus.OK.value(), MessageLoader.getInstance().getMessage(MessagesConstants.IM001), productDtoList);
    }

    @Override
    public ResponseDto<UserDto> getUserById(Long userId) {
        logger.info("Fetching user by ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, userId)));
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), userMapper.toDto(user));
    }

    @Override
    public ResponseDto<UserDto> createUser(UserDto userDto) {
        if (this.userRepository.existsByEmail(userDto.getEmail())){
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM010, MessageLoader.getInstance().getMessage(MessagesConstants.EM010, userDto.getEmail()));
        }
        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        User user = userMapper.toDomain(userDto);
        user.setPassword(hashedPassword);
        final var productSaved = userRepository.save(user);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM002), userMapper.toDto(productSaved));
    }

    @Override
    public ResponseDto<UserDto> updateUser(Long userId, UserDto userDto) {
        logger.info("Updating user with ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, userId)));
        final var userOpt = this.userRepository.findByEmail(userDto.getEmail());
        if (userOpt.isPresent() && !userOpt.get().getId().equals(userId))
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM010, MessageLoader.getInstance().getMessage(MessagesConstants.EM010, userDto.getEmail()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        User updatedProduct = userRepository.save(user);
        UserDto updatedProductDto = userMapper.toDto(updatedProduct);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), updatedProductDto);
    }

    @Override
    public ResponseDto<Void> deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, userId));

        }
        userRepository.deleteById(userId);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001));    }
}
