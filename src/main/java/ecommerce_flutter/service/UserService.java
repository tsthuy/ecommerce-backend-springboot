package ecommerce_flutter.service;

import ecommerce_flutter.dto.request.UserRequestDto;
import ecommerce_flutter.dto.request.UserUpdateRequest;
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
import java.util.Optional;

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
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("user");
        }
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

    public UserResponse login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return userMapper.toUserResponse(user); // Đăng nhập thành công, trả về đối tượng User
        } else {
            return null; // Đăng nhập thất bại
        }
    }

   public UserResponse updateUser(String userId, UserUpdateRequest request){
       User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
       userMapper.updateUser(user, request);
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       return userMapper.toUserResponse(userRepository.save(user));
   }

   public UserResponse getUserById(String userId){
       User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
       return userMapper.toUserResponse(user);
   }

   public void deleteUser(String userId){
       userRepository.deleteById(userId);
   }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public UserResponse register(UserRequestDto userRequestDto) {
        return createUser(userRequestDto);
    }
}
