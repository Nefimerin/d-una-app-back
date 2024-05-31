package com.d.una.app.back.rest;

import com.d.una.app.back.business.IUserBusiness;
import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.domain.UserRequestDto;
import com.d.una.app.back.domain.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class UserRest {
    private final IUserBusiness userBusiness;

    @GetMapping
    public ResponseEntity<ResponseDto<List<UserResponseDto>>> findAllUsers() {
        ResponseDto<List<UserResponseDto>> responseDto = userBusiness.findAllUsers();
        return responseDto.of();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserResponseDto>> getUserById(@PathVariable Long userId) {
        ResponseDto<UserResponseDto> responseDto = userBusiness.getUserById(userId);
        return responseDto.of();
    }

    @PostMapping
    public ResponseEntity<ResponseDto<UserResponseDto>> createUser(@Valid @RequestBody UserRequestDto userDto) {
        ResponseDto<UserResponseDto> responseDto = userBusiness.createUser(userDto);
        return responseDto.of();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseDto<UserResponseDto>> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto userDto) {
        ResponseDto<UserResponseDto> responseDto = userBusiness.updateUser(userId, userDto);
        return responseDto.of();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto<Void>> deleteUser(@PathVariable Long userId) {
        ResponseDto<Void> responseDto = userBusiness.deleteUser(userId);
        return responseDto.of();
    }
}
