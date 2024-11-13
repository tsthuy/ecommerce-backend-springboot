package ecommerce_flutter.mapper;

import ecommerce_flutter.dto.response.ResOrderDTO;
import ecommerce_flutter.model.Order;
import ecommerce_flutter.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public ResOrderDTO mapToResOrderDTO(Order order) {
        if (order == null) {
            return null;
        }

        return ResOrderDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .user(order.getUser())
                .orderItems(order.getOrderItems())
                .build();
    }

}
