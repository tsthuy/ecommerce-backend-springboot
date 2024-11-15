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

            // Dress
            productList.add(Product.builder()
                    .title("BBSTORE'S - D3197 Elegant Long Formal Ruffled Waist Party Dress")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(468000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m2c475f80f5g98.webp")
                    .review("")
                    .category("dress")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Linen sleeveless dress with flared midi flowers")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(269000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-li0hhi0b80xt10.webp")
                    .review("")
                    .category("dress")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Peplum shirt with square flared neck + multi-colored flared long skirt")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(318250)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lhii8yx3b15188.webp")
                    .review("")
                    .category("dress")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("BBSTORE'S Elegant Elastic Waist Long Formal Party Dress - D3561")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(698250)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lvbk1antwj2l32.webp")
                    .review("")
                    .category("dress")
                    .rate(10)
                    .deleted(false)
                    .build());

            // Jacket
            productList.add(Product.builder()
                    .title("Bomber jacket for men and women mixed in 3 colors with Lucky embroidery, super beautiful cotton felt. LOCKED POCKET")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(119000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/54fe8b8fe1fd0670bd14aee2c59d7410.webp")
                    .review("")
                    .category("jacket")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Men's and women's hoodie - Unisex Bomber sweatshirt with the word MISTERHIZII printed, with Korean style 2-layer thick ulzzang hood, Buttons")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(154000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lk7wzajktc5029.webp")
                    .review("")
                    .category("jacket")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Inline badge Unisex Men's and Women's Jeans Jacket, loose denim jacket in Blue, Black and" +
                            " gray Wash bigsize Avocado")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(154000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-ly7ebktuvp29b5.webp")
                    .review("")
                    .category("jacket")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Picolo double-sided silver-coated wind jacket is waterproof, windproof and dustproof")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(125000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lzvn6238hgel14.webp")
                    .review("")
                    .category("jacket")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Genuine 2-layer Milano Basic DSQ2 Bomber Jacket for men and women - Super product DSQ bomber jacket for winter 2025")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(125000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m264rlkd6yeq67.webp")
                    .review("")
                    .category("jacket")
                    .rate(10)
                    .deleted(false)
                    .build());

            // Sweater
            productList.add(Product.builder()
                    .title("Guangzhou Men's Gile Sweater 400gsm Fleece Sweater Unisex V-Neck Cardigan Beautiful Wool Korean Style DYACI AL97")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(525000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7ras8-m27nnt3l8v5g05.webp")
                    .review("")
                    .category("sweater")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Soonvn Men's Collared Sweater DN05")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(325000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lo8ivrg8bn5jd2.webp")
                    .review("")
                    .category("sweater")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("DAVUBA sweatshirt with loose fox fur for men and women SM042")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(325000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/sg-11134201-7rbkd-lnb2qc7yyz1648.webp")
                    .review("")
                    .category("sweater")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("CUNA Sweater Shirt Men's and Women's Sweater Shirt Loose Format Cotton Felt High Quality Imported Exported Plain Round Neck Long Sleeve Local Brand")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(325000)
                    .imageUrl("https://down-vn.img.susercontent.com/file/16e917fc5eb109e4a0dcaec2ae95f243.webp")
                    .review("")
                    .category("sweater")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Autumn and winter casual Japanese-style zippered sweater for men")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(177300)
                    .imageUrl("https://down-vn.img.susercontent.com/file/sg-11134201-22110-8vtxdej4tmjvf0.webp")
                    .review("")
                    .category("sweater")
                    .rate(10)
                    .deleted(false)
                    .build());

            // t-shirt
            productList.add(Product.builder()
                    .title("M-8XL big size men's t-shirt Plus size Sports T-shirt Round Neck Loose Short Sleeve Korean Style For Men")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(267300)
                    .imageUrl("https://down-vn.img.susercontent.com/file/sg-11134201-7qvex-lichqwf11s7h0d.webp")
                    .review("")
                    .category("t-shirt")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("Cotton Sports T-shirt Short Sleeve Round Neck Plus Size M-8XL High Quality Chinese Style For Men")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(167900)
                    .imageUrl("https://down-vn.img.susercontent.com/file/c4f506d8ded9189cd0df948d2b432fea.webp")
                    .review("")
                    .category("t-shirt")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("M-8XL men's t-shirt big size Short Sleeve T-shirt Korean cartoon Air conditioner Cat style print")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(287900)
                    .imageUrl("https://down-vn.img.susercontent.com/file/sg-11134201-7rd4j-lwtuo9szu6pg0d.webp")
                    .review("")
                    .category("t-shirt")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("M-8XL men's t-shirt big size Short Sleeve T-shirt Korean cartoon Air conditioner Cat style print")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(387900)
                    .imageUrl("https://down-vn.img.susercontent.com/file/sg-11134201-7rd5t-lwtuo91iye3we9.webp")
                    .review("")
                    .category("t-shirt")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("M-8XL men's short-sleeved t-shirt, Loose Short-sleeved Cotton T-shirt with Letter Print Summer Fashion for Men")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(127900)
                    .imageUrl("https://down-vn.img.susercontent.com/file/sg-11134201-7qvcw-lf97ejclu3an71.webp")
                    .review("")
                    .category("t-shirt")
                    .rate(10)
                    .deleted(false)
                    .build());
            productList.add(Product.builder()
                    .title("M-8XL men's short sleeve t-shirt, Comfortable Short Sleeve Summer T-shirt with Giant Panda Print")
                    .description("If you don't know how to choose a size, you can inbox us for advice as soon as possible.")
                    .price(425900)
                    .imageUrl("https://down-vn.img.susercontent.com/file/sg-11134201-7qvet-lhjzkn3rx93m60.webp")
                    .review("")
                    .category("t-shirt")
                    .rate(10)
                    .deleted(false)
                    .build());

            productRepository.saveAll(productList);
        }

    }
}
