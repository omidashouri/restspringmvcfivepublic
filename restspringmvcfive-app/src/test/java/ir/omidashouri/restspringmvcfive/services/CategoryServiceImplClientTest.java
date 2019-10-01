package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplClientTest {

    @Autowired @Qualifier("categoryServiceImplClient")
    CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllCategories() {
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        System.out.println("****Category Dto*********");
        categoryDTOS.stream().forEach(categoryDTO -> {
            System.out.println(categoryDTO.getCategoryUrl());
        });
    }

    @Test
    public void getCategoryDtoById() {
        CategoryDTO categoryDTO = categoryService.getCategoryDtoById(33L);
    }
}