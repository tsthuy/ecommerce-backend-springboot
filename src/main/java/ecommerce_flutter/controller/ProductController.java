package ecommerce_flutter.controller;

import com.cloudinary.Api;
import ecommerce_flutter.cloudinary.CloudinaryService;
import ecommerce_flutter.dto.request.ProductCreationRequest;
import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.dto.response.ProductResponse;
import ecommerce_flutter.model.Product;
import ecommerce_flutter.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
//            @RequestParam("review") String review,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("rate") double rate) throws IOException {
        ProductCreationRequest request = ProductCreationRequest.builder()
                .title(title)
                .description(description)
//                .review(review)
                .price(price)
                .category(category)
                .rate(rate)
                .build();
        return ApiResponse.<ProductResponse>builder().result(productService.createProduct(request, file)).build();
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>> getProducts(){
        return ApiResponse.<List<ProductResponse>>builder().result(productService.getProducts()).
                build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable("id") String id){
        return ApiResponse.<Void>builder().result(productService.deleteProduct(id)).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(
            @PathVariable("id") String id,
            @RequestParam(value = "file", required = false) MultipartFile file, // Ảnh là tùy chọn
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("category") String category,
            @RequestParam("rate") double rate) throws IOException {

        ProductCreationRequest request = ProductCreationRequest.builder()
                .title(title)
                .description(description)
                .price(price)
                .category(category)
                .rate(rate)
                .build();

        // Gọi service để cập nhật thông tin sản phẩm và ảnh
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(id, request, file))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable("id") String id){
        return ApiResponse.<ProductResponse>builder().result(productService.getProductById(id)).build();
    }
}
