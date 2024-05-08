package com.d.una.app.back.business;

import com.d.una.app.back.domain.UserDto;
import com.d.una.app.back.domain.ResponseDto;

import java.util.List;

public interface IUserBusiness {
    /**
     * Retrieves all users.
     *
     * @return a ResponseDto containing a list of UserDto objects representing all users in the system.
     */
    ResponseDto<List<UserDto>> findAllUsers();


    /**
     * Retrieves a User based on the provided user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A ResponseDto containing the UserDto information if found, or an appropriate response if not found.
     */
    ResponseDto<UserDto> getUserById(final Long userId);

    /**
     * Creates a new User using the provided UserDto.
     *
     * @param userDto The UserDto containing the information needed to create the new user.
     * @return A ResponseDto indicating the result of the creation process, including the newly created UserDto.
     */
    ResponseDto<UserDto> createUser(final UserDto userDto);

    /**
     * Updates an existing User identified by the provided user ID using the new information in UserDto.
     *
     * @param userId  The ID of the user to update.
     * @param userDto The UserDto containing the updated user information.
     * @return A ResponseDto indicating the result of the update process, including the updated UserDto.
     */
    ResponseDto<UserDto> updateUser(final Long userId, final UserDto userDto);

    /**
     * Deletes a User based on the provided user ID.
     *
     * @param userId The ID of the user to delete.
     * @return A ResponseDto indicating the result of the deletion process.
     */
    ResponseDto<Void> deleteUser(final Long userId);
}
