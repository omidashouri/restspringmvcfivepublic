package ir.omidashouri.restspringmvcfive.controllers;

import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import ir.omidashouri.restspringmvcfive.services.CategoryService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

public class CategoryControllerTest {

    public static final long ID1 = 1L;
    public static final String NAME1 = "omid1";
    public static final long ID2 = 2L;
    public static final String NAME2 = "omid2";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

//        no need to bellow with @InjectMocks
//        categoryController = new CategoryController(categoryService);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testListCategories() throws Exception{

//        given
        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(ID1);
        categoryDTO1.setName(NAME1);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setId(ID2);
        categoryDTO2.setName(NAME2);

        List<CategoryDTO> categoryDTOS = Arrays.asList(categoryDTO1,categoryDTO2);

//        when
        Mockito.when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

//        then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/")
                            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categories", Matchers.hasSize(2)));

//     mockMvc.perform(get("/api/v1/categories/")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.categories", hasSize(2)));

    }

    @Test
    public void testGetByNameCategories() throws Exception{

//        given
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID1);
        categoryDTO.setName(NAME1);

//        when
        Mockito.when(categoryService.getCategoryByName(ArgumentMatchers.anyString())).thenReturn(categoryDTO);

//        then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/omid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.equalTo(NAME1)));

    }

}