package se.lexicon.ecommerce.mapper;

import org.springframework.stereotype.Component;
import se.lexicon.ecommerce.dto.ProductRequest;
import se.lexicon.ecommerce.dto.ProductResponse;
import se.lexicon.ecommerce.entity.Category;
import se.lexicon.ecommerce.entity.Product;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName()
        );
    }

    public Product toEntity(ProductRequest request, Category category) {
        return new Product(
                request.name(),
                request.price(),
                category
        );
    }
}