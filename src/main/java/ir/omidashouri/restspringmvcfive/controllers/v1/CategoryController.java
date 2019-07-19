package ir.omidashouri.restspringmvcfive.controllers.v1;


import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import ir.omidashouri.restspringmvcfive.model.CategoryListDTO;
import ir.omidashouri.restspringmvcfive.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping(CategoryController.CATEGORIES_URL)
public class CategoryController {

    public static final String CATEGORIES_URL = "/api/v1/categories/";

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);

    }


    /*
    * instead @Controller we can use @RestController which have '@Controller' and '@ResponseBody'
    *
    * the for example first method:
    *
    *     @GetMapping
          public ResponseEntity<CategoryListDTO> getAllCategories(){
            return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
          }
    *
    * change to:
    *
          @GetMapping
          @ResponseStatus(HttpStatus.OK)
          public CategoryListDTO getAllCategories(){
                return new CategoryListDTO(categoryService.getAllCategories());
          }
    *
    *
    * because in rest we need Object for JASON but in MVC we need string
    * */

}
