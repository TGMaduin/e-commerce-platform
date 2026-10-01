package se.lexicon.ecommerce.service;

import se.lexicon.ecommerce.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(String name);

    List<CategoryResponse> findAll();
}