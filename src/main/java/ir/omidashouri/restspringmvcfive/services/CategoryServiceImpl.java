package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.mapper.CategoryMapper;
import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import ir.omidashouri.restspringmvcfive.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {


    private final CategoryMapper  categoryMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name)
    {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
