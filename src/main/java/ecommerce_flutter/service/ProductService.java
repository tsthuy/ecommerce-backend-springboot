package ecommerce_flutter.service;

import ecommerce_flutter.cloudinary.CloudinaryService;
import ecommerce_flutter.dto.request.ProductCreationRequest;
import ecommerce_flutter.dto.response.ProductResponse;
import ecommerce_flutter.exception.AppException;
import ecommerce_flutter.mapper.ProductMapper;
import ecommerce_flutter.model.Product;
import ecommerce_flutter.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static ecommerce_flutter.exception.ErrorCode.PRODUCT_NOT_EXISTED;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    CloudinaryService cloudinaryService;
    ProductMapper productMapper;

    public ProductResponse createProduct(ProductCreationRequest request, MultipartFile file) throws IOException {
        Product product = productMapper.toProduct(request);
        String imageUrl = cloudinaryService.uploadImage(file);
        product.setImageUrl(imageUrl);
        try {
            product = productRepository.save(product);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to save product to database", ex);
        }
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAllNotDeleted().stream().map(productMapper::toProductResponse).toList();
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new AppException(PRODUCT_NOT_EXISTED));
        return productMapper.toProductResponse(product);
    }

    public Void deleteProduct(String id) {
        Product productDb = productRepository.findById(id).orElseThrow(() -> new AppException(PRODUCT_NOT_EXISTED));
        productDb.setDeleted(true);
        productRepository.save(productDb);
        return null;
    }

    public ProductResponse updateProduct(String id, ProductCreationRequest request, MultipartFile file) throws IOException {
        // Tìm sản phẩm theo ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(PRODUCT_NOT_EXISTED));

        // Cập nhật các thuộc tính sản phẩm
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(request.getCategory());
        product.setRate(request.getRate());

        // Nếu có ảnh mới, upload ảnh lên Cloudinary và cập nhật URL
        if (file != null && !file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(file);
            product.setImageUrl(imageUrl); // Cập nhật URL ảnh mới
        }


            product = productRepository.save(product);


        // Trả về ProductResponse sau khi cập nhật thành công
        return productMapper.toProductResponse(product);
    }

}
