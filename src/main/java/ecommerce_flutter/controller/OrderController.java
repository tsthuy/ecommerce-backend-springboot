package ecommerce_flutter.controller;

import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.dto.response.ResOrderDTO;
import ecommerce_flutter.dto.response.UserResponse;
import ecommerce_flutter.model.Order;
import ecommerce_flutter.model.OrderItem;
import ecommerce_flutter.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ApiResponse<Order> getById(@PathVariable("id") String id)  {
        return ApiResponse.<Order>builder().result(orderService.getOrderById(id))
                .build();    }

    @PostMapping
    public ApiResponse<Order> create(@Valid @RequestBody Order order){
        return ApiResponse.<Order>builder().result(orderService.createOrder(order))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ResOrderDTO>> getOrders(){
        return ApiResponse.<List<ResOrderDTO>>builder().result(orderService.getAllOrderByAdmin())
                .build();
    }

}
