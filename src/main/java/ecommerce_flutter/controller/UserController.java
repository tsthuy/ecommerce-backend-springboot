package ecommerce_flutter.controller;

import ecommerce_flutter.dto.request.UserRequestDto;
import ecommerce_flutter.dto.request.UserUpdateRequest;
import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.dto.response.UserResponse;
import ecommerce_flutter.model.User;
import ecommerce_flutter.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping("/")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        return ApiResponse.<UserResponse>builder().result(userService.createUser(userRequestDto))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUers(){
        return ApiResponse.<List<UserResponse>>builder().result(userService.getUsers()).build();
    }

    @PostMapping("/username")
    ApiResponse<UserResponse> getUserByUsername(@RequestBody String username){
        return ApiResponse.<UserResponse>builder().result(userService.getUserByUsername(username)).build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId , @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return ApiResponse.<String>builder().result("User has been deleted!").build();
    }
}
