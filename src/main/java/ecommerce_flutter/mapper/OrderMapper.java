package ecommerce_flutter.mapper;

import ecommerce_flutter.dto.response.OrderResponse;
import ecommerce_flutter.model.Item;
import ecommerce_flutter.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "status", target = "status")
    OrderResponse toOrderResponse(Order order);
    OrderResponse.ItemResponse toItemResponse(Item item);
}
