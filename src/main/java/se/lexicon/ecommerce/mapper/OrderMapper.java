package se.lexicon.ecommerce.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerce.dto.OrderItemRequest;
import se.lexicon.ecommerce.dto.OrderItemResponse;
import se.lexicon.ecommerce.dto.OrderRequest;
import se.lexicon.ecommerce.dto.OrderResponse;
import se.lexicon.ecommerce.entity.Customer;
import se.lexicon.ecommerce.entity.Order;
import se.lexicon.ecommerce.entity.OrderItem;
import se.lexicon.ecommerce.entity.Product;

import java.util.List;
import java.util.Map;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        List<OrderItemResponse> items = order.getOrderItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getUnitPrice()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getCustomer().getId(),
                order.getOrderDate(),
                order.getStatus(),
                items
        );
    }

    public Order toEntity(
            OrderRequest request,
            Customer customer,
            Map<Long, Product> products
    ) {
        Order order = new Order(customer);

        for (OrderItemRequest itemRequest : request.items()) {
            Product product = products.get(itemRequest.productId());

            OrderItem orderItem = new OrderItem(
                    itemRequest.quantity(),
                    product
            );

            order.addOrderItem(orderItem);
        }

        return order;
    }
}