package ecommerce_flutter.controller;

import ecommerce_flutter.dto.request.UserRequestDto;
import ecommerce_flutter.dto.request.UserUpdateRequest;
import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.dto.response.UserResponse;
import ecommerce_flutter.model.User;
import ecommerce_flutter.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping("/register")
    ApiResponse<UserResponse> createUser(@RequestBody UserRequestDto userRequestDto){
//        log.info(userRequestDto.getUsername(), userRequestDto.getPassword());
        return ApiResponse.<UserResponse>builder().result(userService.createUser(userRequestDto))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@RequestBody UserRequestDto loginRequest) {
//        log.info(loginRequest.getUsername(), loginRequest.getPassword());
        return ApiResponse.<UserResponse>builder().result(userService.login(loginRequest.getUsername(), loginRequest.getPassword())).build();
    }
    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        return ApiResponse.<List<UserResponse>>builder().result(userService.getUsers()).build();
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
