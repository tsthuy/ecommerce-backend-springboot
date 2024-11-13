package ecommerce_flutter.service;

import ecommerce_flutter.dto.request.ReqOrderItem;
import ecommerce_flutter.model.OrderItem;
import ecommerce_flutter.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem getOrderItemById(String id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItem createOrderItem(ReqOrderItem orderItem) {
        OrderItem newOrderItem = new OrderItem().toBuilder()
                .order(orderItem.getOrder())
                .product(orderItem.getProduct())
                .quantity(orderItem.getQuantity())
                .build();
        return orderItemRepository.save(newOrderItem);
    }
}
