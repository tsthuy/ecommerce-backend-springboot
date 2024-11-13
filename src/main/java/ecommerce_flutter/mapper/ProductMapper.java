package ecommerce_flutter.mapper;

import ecommerce_flutter.dto.request.ProductCreationRequest;
import ecommerce_flutter.dto.request.ProductUpdateRequest;
import ecommerce_flutter.dto.response.ProductResponse;
import ecommerce_flutter.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest request);
//    ProductResponse toProductResponse(Product product);
default ProductResponse toProductResponse(Product product) {
    ProductResponse response = new ProductResponse();
    response.setId(product.getId());
    response.setTitle(product.getTitle());
    response.setDescription(product.getDescription());
    response.setImageUrl(product.getImageUrl());
    response.setReview(product.getReview());
    response.setPrice(product.getPrice());
    response.setCategory(product.getCategory());
    response.setRate(product.getRate());
    System.out.println("Seller in response: " + response.getSeller());  // Log giá trị seller
    return response;
}
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
