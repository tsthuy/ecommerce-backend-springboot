package ecommerce_flutter.controller;

import ecommerce_flutter.dto.request.OrderRequest;
import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.dto.response.OrderResponse;
import ecommerce_flutter.model.Item;
import ecommerce_flutter.model.Order;
import ecommerce_flutter.repository.OrderRepository;
import ecommerce_flutter.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    //    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    OrderRepository orderRepository;
    OrderService orderService;
    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setUserId(orderRequest.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("pending");

        // Duyệt qua tất cả các Item trong orderRequest
        for (Item item : orderRequest.getItems()) {
            Item itemInOrder = new Item();
            itemInOrder.setOrder(order);
            itemInOrder.setTitle(item.getTitle());
            itemInOrder.setImageUrl(item.getImageUrl());
            itemInOrder.setQuantity(item.getQuantity());
            itemInOrder.setPrice(item.getPrice());

            order.getItems().add(itemInOrder);
        }
        orderRepository.save(order);

        return ResponseEntity.ok("Order placed successfully");

    }
    @GetMapping
    ApiResponse<List<OrderResponse>> getOrders(){
        return ApiResponse.<List<OrderResponse>>builder().result(orderService.getOders()).build();
    }
    @GetMapping("/{userId}")
    ApiResponse<List<OrderResponse>> getOrderByUserId(@PathVariable String userId){
        return ApiResponse.<List<OrderResponse>>builder().result(orderService.getOrderById(userId)).build();
    }
    @PatchMapping("/{orderId}")
    public ApiResponse<OrderResponse> updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        OrderResponse updatedOrder = orderService.updateOrderStatus(orderId, status);
        return ApiResponse.<OrderResponse>builder().result(updatedOrder).build();
    }
}