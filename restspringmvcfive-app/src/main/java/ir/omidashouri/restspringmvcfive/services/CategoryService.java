package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

    CategoryDTO getCategoryDtoById(Long id);
}
