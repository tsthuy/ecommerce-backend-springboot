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
    ProductResponse toProductResponse(Product product);
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);
}
