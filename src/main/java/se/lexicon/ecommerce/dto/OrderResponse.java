package se.lexicon.ecommerce.dto;

import se.lexicon.ecommerce.entity.enums.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long id,
        Long customerId,
        Instant orderDate,
        OrderStatus status,
        List<OrderItemResponse> items
) {
}