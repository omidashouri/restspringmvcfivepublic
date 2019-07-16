package ir.omidashouri.restspringmvcfive.controllers.v1;


import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import ir.omidashouri.restspringmvcfive.model.CategoryListDTO;
import ir.omidashouri.restspringmvcfive.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CategoryController.CATEGORIES_URL)
public class CategoryController {

    public static final String CATEGORIES_URL = "/api/v1/categories/";

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){

        return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<CategoryDTO>(
                categoryService.getCategoryByName(name),HttpStatus.OK);

    }

}
