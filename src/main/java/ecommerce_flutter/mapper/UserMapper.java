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
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
