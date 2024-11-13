package ecommerce_flutter.service;

import ecommerce_flutter.dto.response.ResOrderDTO;
import ecommerce_flutter.dto.response.UserResponse;
import ecommerce_flutter.mapper.OrderMapper;
import ecommerce_flutter.mapper.UserMapper;
import ecommerce_flutter.model.Order;
import ecommerce_flutter.model.User;
import ecommerce_flutter.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order createOrder(Order order) {
        System.out.println(order);
        UserResponse resDbUser = userService.getUserById(order.getUser().getId());
        User dbUser = new User();
        dbUser.setId(resDbUser.getId());
        dbUser.setUsername(resDbUser.getUsername());
        dbUser.setRole("user");

        Order newOrder = new Order();
        newOrder.setUser(dbUser);
        return orderRepository.save(order);
    }

    public List<ResOrderDTO> getAllOrderByAdmin() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(orderMapper::mapToResOrderDTO)
                .collect(Collectors.toList());
    }

    public Order updateOrder(Order orderUpdate, String id) {
        Order orderDb = getOrderById(id);

        orderDb.setStatus(orderUpdate.getStatus());
        return orderRepository.save(orderDb);
    }
}
