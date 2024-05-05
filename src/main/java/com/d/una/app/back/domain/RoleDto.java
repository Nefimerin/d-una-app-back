package com.d.una.app.back.domain;

import com.d.una.app.back.model.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long roleId;
    @NotBlank
    @NotEmpty
    private RoleEnum name;
    @NotBlank
    @NotEmpty
    private String description;
}
