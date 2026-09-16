package se.lexicon.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.ecommerce.entity.Order;
import se.lexicon.ecommerce.entity.enums.OrderStatus;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer_Email(String email);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByOrderDateBetween(Instant start, Instant end);
}