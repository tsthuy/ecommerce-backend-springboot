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
        } catch (DataAccessException ex){
            throw new RuntimeException("Failed to save product to database", ex);
        }
        return productMapper.toProductResponse(product);
    }
    public List<ProductResponse> getProducts(){

        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }
}
