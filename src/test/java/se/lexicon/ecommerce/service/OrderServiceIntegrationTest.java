package se.lexicon.ecommerce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.ecommerce.dto.OrderItemRequest;
import se.lexicon.ecommerce.dto.OrderRequest;
import se.lexicon.ecommerce.dto.OrderResponse;
import se.lexicon.ecommerce.entity.Address;
import se.lexicon.ecommerce.entity.Category;
import se.lexicon.ecommerce.entity.Customer;
import se.lexicon.ecommerce.entity.Product;
import se.lexicon.ecommerce.exception.ResourceNotFoundException;
import se.lexicon.ecommerce.repository.CategoryRepository;
import se.lexicon.ecommerce.repository.CustomerRepository;
import se.lexicon.ecommerce.repository.OrderRepository;
import se.lexicon.ecommerce.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        customerRepository.deleteAll();

        Address address = new Address(
                "Test Street 1",
                "Malmö",
                "21100"
        );

        customer = customerRepository.save(
                new Customer(
                        "Test",
                        "Customer",
                        "test@example.com",
                        address
                )
        );

        Category category = categoryRepository.save(
                new Category("Electronics")
        );

        product = productRepository.save(
                new Product(
                        "Gaming Mouse",
                        new BigDecimal("599.00"),
                        category
                )
        );
    }

    @Test
    void placeOrderShouldCreateOrderWithOrderItem() {
        OrderItemRequest itemRequest = new OrderItemRequest(
                product.getId(),
                2
        );

        OrderRequest request = new OrderRequest(
                customer.getId(),
                List.of(itemRequest)
        );

        OrderResponse response = orderService.placeOrder(request);

        assertNotNull(response.id());
        assertEquals(customer.getId(), response.customerId());
        assertEquals(1, response.items().size());

        assertEquals(product.getId(), response.items().getFirst().productId());
        assertEquals(2, response.items().getFirst().quantity());
        assertEquals(
                new BigDecimal("599.00"),
                response.items().getFirst().unitPrice()
        );

        assertEquals(1, orderRepository.count());
    }

    @Test
    void placeOrderShouldNotCreateOrderWhenProductDoesNotExist() {
        OrderItemRequest itemRequest = new OrderItemRequest(
                999999L,
                2
        );

        OrderRequest request = new OrderRequest(
                customer.getId(),
                List.of(itemRequest)
        );

        assertThrows(
                ResourceNotFoundException.class,
                () -> orderService.placeOrder(request)
        );

        assertEquals(0, orderRepository.count());
    }
}