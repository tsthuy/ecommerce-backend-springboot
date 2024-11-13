package ecommerce_flutter.configuaration;

import ecommerce_flutter.dto.request.UserRequestDto;
import ecommerce_flutter.dto.response.UserResponse;
import ecommerce_flutter.model.Product;
import ecommerce_flutter.model.User;
import ecommerce_flutter.repository.ProductRepository;
import ecommerce_flutter.repository.UserRepository;
import ecommerce_flutter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    @Override
    public void run(String ...args) throws Exception {
        long countUser = userRepository.count();
        long countProduct = productRepository.count();

        if (countUser == 0) {
            ArrayList<User> users = new ArrayList<User>();
            // tạo 1 user
            User user = new User();
            user.setPassword(passwordEncoder.encode("user"));
            user.setUsername("user");
            user.setRole("user");
            userRepository.save(user);

            // tạo 1 admin
            User admin = new User();
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setUsername("admin");
            admin.setRole("admin");
            userRepository.save(admin);
        }

        if (countProduct == 0) {
            List<Product> productList = new ArrayList<>();

            productList.add(Product.builder()
                    .title("Quần Jogger Pants French Terry")
                    .description("Kiểu dệt French Terry giúp tăng cường khả năng thoáng khí, làm mát mẻ hơn khi mặc trong thời tiết ấm")
                    .price(379000)
                    .imageUrl("https://media3.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/November2024/24CMCW.TN003_IMG_8074_XAM_93.JPG")
                    .review("")
                    .category("trousers")
                    .rate(10)
                    .build());

            productList.add(Product.builder()
                    .title("Quần shorts 6 inch Racquet Sports\n")
                    .description("Kiểu dệt French Terry giúp tăng cường khả năng thoáng khí, làm mát mẻ hơn khi mặc trong thời tiết ấm")
                    .price(189000)
                    .imageUrl("https://media3.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/September2024/quan_cau_long_(8).jpg")
                    .review("")
                    .category("trousers")
                    .rate(10)
                    .build());

            productList.add(Product.builder()
                    .title("Áo phao nhẹ Ultrawarm")
                    .description("Kiểu dệt French Terry giúp tăng cường khả năng thoáng khí, làm mát mẻ hơn khi mặc trong thời tiết ấm")
                    .price(379000)
                    .imageUrl("https://media3.coolmate.me/cdn-cgi/image/quality=80,format=auto/uploads/November2024/24CMCW.KM006_-_NAVY_1.jpg")
                    .review("")
                    .category("jacket")
                    .rate(10)
                    .build());

            productList.add(Product.builder()
                    .title("Chân Váy Công Sở\n")
                    .description("Kiểu dệt French Terry giúp tăng cường khả năng thoáng khí, làm mát mẻ hơn khi mặc trong thời tiết ấm")
                    .price(220000)
                    .imageUrl("https://product.hstatic.net/1000163008/product/1_82d2dc8b80b24f6cad5707c7c1a28ec6_master.jpg")
                    .review("")
                    .category("skirt")
                    .rate(10)
                    .build());

            productRepository.saveAll(productList);
        }

    }
}
