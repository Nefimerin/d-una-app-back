package com.d.una.app.back.domain;

import com.d.una.app.back.model.enums.RoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private RoleEnum name;
    @NotBlank
    @NotEmpty
    private String description;
}
