package com.d.una.app.back.business;

import com.d.una.app.back.domain.RoleDto;
import com.d.una.app.back.domain.ResponseDto;

import java.util.List;

public interface IRoleBusiness {

    ResponseDto<List<RoleDto>> findAllRoles();


    /**
     * Retrieves a Role based on the provided role ID.
     *
     * @param roleId The ID of the role to retrieve.
     * @return A ResponseDto containing the RoleDto information if found, or an appropriate response if not found.
     */
    ResponseDto<RoleDto> getRoleById(final Long roleId);

    /**
     * Creates a new Role using the provided RoleDto.
     *
     * @param roleDto The RoleDto containing the information needed to create the new role.
     * @return A ResponseDto indicating the result of the creation process, including the newly created RoleDto.
     */
    ResponseDto<RoleDto> createRole(final RoleDto roleDto);

    /**
     * Updates an existing Role identified by the provided role ID using the new information in RoleDto.
     *
     * @param roleId  The ID of the role to update.
     * @param roleDto The RoleDto containing the updated role information.
     * @return A ResponseDto indicating the result of the update process, including the updated RoleDto.
     */
    ResponseDto<RoleDto> updateRole(final Long roleId, final RoleDto roleDto);

    /**
     * Deletes a Role based on the provided role ID.
     *
     * @param roleId The ID of the role to delete.
     * @return A ResponseDto indicating the result of the deletion process.
     */
    ResponseDto<Void> deleteRole(final Long roleId);
}
