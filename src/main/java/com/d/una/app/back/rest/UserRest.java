package com.d.una.app.back.rest;

import com.d.una.app.back.business.IUserBusiness;
import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.domain.UserDto;
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
    public ResponseEntity<ResponseDto<List<UserDto>>> findAllUsers() {
        ResponseDto<List<UserDto>> responseDto = userBusiness.findAllUsers();
        return responseDto.of();
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ResponseDto<UserDto>> getProductById(@PathVariable Long roleId) {
        ResponseDto<UserDto> responseDto = userBusiness.getUserById(roleId);
        return responseDto.of();
    }

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> createProduct(@Valid @RequestBody UserDto roleDto) {
        ResponseDto<UserDto> responseDto = userBusiness.createUser(roleDto);
        return responseDto.of();
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ResponseDto<UserDto>> updateProduct(@PathVariable Long roleId, @Valid @RequestBody UserDto roleDto) {
        ResponseDto<UserDto> responseDto = userBusiness.updateUser(roleId, roleDto);
        return responseDto.of();
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ResponseDto<Void>> deleteProduct(@PathVariable Long roleId) {
        ResponseDto<Void> responseDto = userBusiness.deleteUser(roleId);
        return responseDto.of();
    }
}
