package com.d.una.app.back.business;

import com.d.una.app.back.config.MessageLoader;
import com.d.una.app.back.domain.ResponseDto;
import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.exception.BusinessRuleException;
import com.d.una.app.back.mapper.IRoleMapper;
import com.d.una.app.back.model.Role;
import com.d.una.app.back.repository.IRoleRepository;
import com.d.una.app.back.util.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class RoleBusinessImpl implements IRoleBusiness{
    private static final Logger logger = LoggerFactory.getLogger(RoleBusinessImpl.class);
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;
    @Override
    public ResponseDto<List<RoleDto>> findAllRoles() {
        logger.info("Fetching all products");
        List<Role> productList = roleRepository.findAll();
        List<RoleDto> productDtoList = productList.stream()
                .map(roleMapper::toDto)
                .toList();
        return new ResponseDto<>(HttpStatus.OK.value(), MessageLoader.getInstance().getMessage(MessagesConstants.IM001), productDtoList);
    }

    @Override
    public ResponseDto<RoleDto> getRoleById(Long roleId) {
        logger.info("Fetching role by ID: {}", roleId);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, roleId)));
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), roleMapper.toDto(role));
    }

    @Override
    public ResponseDto<RoleDto> createRole(RoleDto roleDto) {
        if (this.roleRepository.existsByName(roleDto.getName())){
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM009, MessageLoader.getInstance().getMessage(MessagesConstants.EM009, roleDto.getName()));
        }
        Role role = roleMapper.toDomain(roleDto);
        final var productSaved = roleRepository.save(role);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM002), roleMapper.toDto(productSaved));
    }

    @Override
    public ResponseDto<RoleDto> updateRole(Long roleId, RoleDto roleDto) {
        logger.info("Updating role with ID: {}", roleId);

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, roleId)));
        final var roleOpt = this.roleRepository.findByName(roleDto.getName());
        if (roleOpt.isPresent() && !roleOpt.get().getRoleId().equals(roleId))
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM009, MessageLoader.getInstance().getMessage(MessagesConstants.EM009, roleDto.getName()));
        role.setDescription(roleDto.getDescription());
        role.setName(roleDto.getName());
        Role updatedProduct = roleRepository.save(role);
        RoleDto updatedProductDto = roleMapper.toDto(updatedProduct);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001), updatedProductDto);
    }

    @Override
    public ResponseDto<Void> deleteRole(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, roleId));

        }
        roleRepository.deleteById(roleId);
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001));
    }
}
