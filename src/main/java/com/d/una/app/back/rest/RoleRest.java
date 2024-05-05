package com.d.una.app.back.rest;

import com.d.una.app.back.business.IRoleBusiness;
import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.domain.ResponseDto;
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
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class RoleRest {
    private final IRoleBusiness roleBusiness;

    @GetMapping
    public ResponseEntity<ResponseDto<List<RoleDto>>> findAllRoles() {
        ResponseDto<List<RoleDto>> responseDto = roleBusiness.findAllRoles();
        return responseDto.of();
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<ResponseDto<RoleDto>> getProductById(@PathVariable Long roleId) {
        ResponseDto<RoleDto> responseDto = roleBusiness.getRoleById(roleId);
        return responseDto.of();
    }

    @PostMapping
    public ResponseEntity<ResponseDto<RoleDto>> createProduct(@RequestBody RoleDto roleDto) {
        ResponseDto<RoleDto> responseDto = roleBusiness.createRole(roleDto);
        return responseDto.of();
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<ResponseDto<RoleDto>> updateProduct(@PathVariable Long roleId, @RequestBody RoleDto roleDto) {
        ResponseDto<RoleDto> responseDto = roleBusiness.updateRole(roleId, roleDto);
        return responseDto.of();
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<ResponseDto<Void>> deleteProduct(@PathVariable Long roleId) {
        ResponseDto<Void> responseDto = roleBusiness.deleteRole(roleId);
        return responseDto.of();
    }
}
