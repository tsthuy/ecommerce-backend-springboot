package ecommerce_flutter.controller;

import ecommerce_flutter.cloudinary.CloudinaryService;
import ecommerce_flutter.dto.request.ProductCreationRequest;
import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.dto.response.ProductResponse;
import ecommerce_flutter.model.Product;
import ecommerce_flutter.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ProductController {
    CloudinaryService cloudinaryService;
    ProductService productService;


    @PostMapping
    public ApiResponse<ProductResponse> createProduct(
            @RequestParam("file") MultipartFile file,  // nhận ảnh
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("review") String review,
            @RequestParam("seller") String seller,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("rate") double rate) throws IOException {
        ProductCreationRequest request = ProductCreationRequest.builder()
                .title(title)
                .description(description)
                .review(review)
                .Seller(seller)
                .price(price)
                .category(category)
                .rate(rate)
                .build();
        return ApiResponse.<ProductResponse>builder().result(productService.createProduct(request, file)).build();
    }

}
