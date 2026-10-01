package se.lexicon.ecommerce.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.ecommerce.dto.CategoryResponse;
import se.lexicon.ecommerce.entity.Category;
import se.lexicon.ecommerce.repository.CategoryRepository;
import se.lexicon.ecommerce.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponse create(String name) {
        categoryRepository.findByNameIgnoreCase(name)
                .ifPresent(category -> {
                    throw new IllegalArgumentException(
                            "Category with name " + name + " already exists."
                    );
                });

        Category category = new Category(name);
        Category savedCategory = categoryRepository.save(category);

        return new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(
                        category.getId(),
                        category.getName()
                ))
                .toList();
    }
}
