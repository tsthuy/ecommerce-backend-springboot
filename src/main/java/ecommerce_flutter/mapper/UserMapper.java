package ecommerce_flutter.mapper;

import ecommerce_flutter.dto.request.UserRequestDto;
import ecommerce_flutter.dto.request.UserUpdateRequest;
import ecommerce_flutter.dto.response.UserResponse;
import ecommerce_flutter.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDto requestDto);
    default UserResponse toUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());
        return userResponse;
    };

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
