package ecommerce_flutter.controller;

import ecommerce_flutter.dto.request.ReqOrderItem;
import ecommerce_flutter.dto.response.ApiResponse;
import ecommerce_flutter.model.Order;
import ecommerce_flutter.model.OrderItem;
import ecommerce_flutter.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderItem")
@RequiredArgsConstructor
public class OrderItemController
{
    private final OrderItemService orderItemService;

    @GetMapping("/{id}")
    public ApiResponse<OrderItem> getById(@PathVariable("id") String id)  {
        return ApiResponse.<OrderItem>builder()
                .result(orderItemService.getOrderItemById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<OrderItem> create(@Valid @RequestBody ReqOrderItem orderItem){
        return ApiResponse.<OrderItem>builder()
                .result(orderItemService.createOrderItem(orderItem))
                .build();
    }
}
