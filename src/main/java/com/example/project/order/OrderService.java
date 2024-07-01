package com.example.project.order;

import com.example.project.appuser.AppUser;
import com.example.project.product.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductService productService;

    public void createOrder(Principal connectedUser) {

        var user = (AppUser) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Order order = Order.builder()
                .appUser(user)
                .status(STATUS.PENDING)
                .createdDate(LocalDate.now())
                .build();
        orderRepository.save(order);
    }

    public void addProductToOrder(OrderRequest orderRequest) {

        if(orderRequest.getQuantity() > productService.getProductById(orderRequest.getProductId()).getQuantity()){
            throw new RuntimeException("Not enough quantity");
        }
        OrderDetails orderDetails = OrderDetails.builder()
                .order(orderRepository.findById(orderRequest.getOrderId()).orElseThrow(
                        () -> new RuntimeException("Order not found")))
                .product(productService.getProductById(orderRequest.getProductId()))
                .quantity(orderRequest.getQuantity())
                .build();

        orderDetailsRepository.save(orderDetails);
    }

    public List<String> showAllOrderDetails(Integer orderId) {
        boolean orderExists = orderRepository.existsById(orderId);
        if (!orderExists) {
            throw new RuntimeException("Order not found");
        }
        List<OrderDetails> orderDetails = orderDetailsRepository.findAllByOrderId(orderId);
        return orderDetails.stream().map(
                orderDetail ->
                        "product id: "+orderDetail.getProduct().getId() +
                        "; product name: " + orderDetail.getProduct().getProductName() +
                                "; product quantity:" + orderDetail.getQuantity()
        ).toList();
    }


    public List<Integer> showAllOrderIds() {
        return orderRepository.findAll().stream().map(Order::getId).toList();
    }
}
