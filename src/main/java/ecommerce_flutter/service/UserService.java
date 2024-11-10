package ecommerce_flutter.service;

import ecommerce_flutter.dto.request.UserRequestDto;
import ecommerce_flutter.dto.request.UserUpdateRequest;
import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.dto.response.ProductResponse;
import ecommerce_flutter.dto.response.UserResponse;
import ecommerce_flutter.exception.AppException;
import ecommerce_flutter.exception.ErrorCode;
import ecommerce_flutter.mapper.UserMapper;
import ecommerce_flutter.model.User;
import ecommerce_flutter.repository.ProductRepository;
import ecommerce_flutter.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserMapper userMapper;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    public UserResponse createUser(UserRequestDto userRequestDto){
        User user = userMapper.toUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        try {
            user = userRepository.save(user);
        } catch(DataIntegrityViolationException ex) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userMapper.toUserResponse(user);
    }
   public List<UserResponse> getUsers(){
       return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
   }
   public UserResponse updateUser(String userId, UserUpdateRequest request){
       User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
       userMapper.updateUser(user, request);
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       return userMapper.toUserResponse(userRepository.save(user));
   }

   public void deleteUser(String userId){
       userRepository.deleteById(userId);
   }

}
