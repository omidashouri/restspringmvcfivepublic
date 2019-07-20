package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.domain.Category;
import ir.omidashouri.restspringmvcfive.mapper.CategoryMapper;
import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import ir.omidashouri.restspringmvcfive.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "omid";
    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE,categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception{

//        given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

//        when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

//        then
        assertEquals(3,categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() throws Exception{

//        given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        Mockito.when(categoryRepository.findByName(ArgumentMatchers.anyString())).thenReturn(category);

//        when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

//        then
        assertEquals(ID,categoryDTO.getId());
        assertEquals(NAME,categoryDTO.getName());
    }
}