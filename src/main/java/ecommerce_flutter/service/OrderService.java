package ecommerce_flutter.service;

import ecommerce_flutter.dto.response.OrderResponse;
import ecommerce_flutter.mapper.OrderMapper;
import ecommerce_flutter.model.Order;
import ecommerce_flutter.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public  class OrderService {
    OrderMapper orderMapper;
    OrderRepository orderRepository;
    public List<OrderResponse> getOders(){
       List<Order> orders = orderRepository.findAll();
       return orders.stream().map(orderMapper::toOrderResponse).toList();
    }
   public List<OrderResponse> getOrderById(String userId){
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(orderMapper::toOrderResponse).toList();
    }
    public OrderResponse updateOrderStatus(String orderId, String status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);

        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            return orderMapper.toOrderResponse(orderRepository.save(order));  // Lưu thay đổi vào DB
            // Trả về OrderResponse đã cập nhật
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }
}