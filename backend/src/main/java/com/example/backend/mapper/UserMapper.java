package com.example.backend.mapper;

import com.example.backend.dto.ProfileDto;
import com.example.backend.dto.SignUpDto;
import com.example.backend.dto.UserDto;
import com.example.backend.dto.UserSummaryDto;
import com.example.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by Binh
 * Date : 7/27/2023 - 11:14 PM
 * Description :
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    UserSummaryDto toUserSummary(User user);
    List<UserSummaryDto> toUserSummaryDtos(List<User> users);

    @Mapping(target = "userDto.id", source = "id")
    @Mapping(target = "userDto.firstName", source = "firstName")
    @Mapping(target = "userDto.lastName", source = "lastName")
    ProfileDto userToProfileDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
