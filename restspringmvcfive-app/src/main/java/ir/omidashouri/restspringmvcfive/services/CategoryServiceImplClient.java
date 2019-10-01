package ir.omidashouri.restspringmvcfive.services;


import ir.omidashouri.restspringmvcfive.model.CategoryDTO;
import ir.omidashouri.restspringmvcfive.model.CategoryListDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class CategoryServiceImplClient implements CategoryService {

    private RestTemplate restTemplate;

    private String api_category_url;

    public CategoryServiceImplClient(RestTemplate restTemplate,@Value("${api.category.url}") String api_category_url) {
        this.restTemplate = restTemplate;
        this.api_category_url = api_category_url;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(api_category_url);

        CategoryListDTO categoryListDTO =  restTemplate.getForObject(uriComponentsBuilder.toUriString(), CategoryListDTO.class);
        return categoryListDTO.getCategoryDTOList();
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return null;
    }

    @Override
    public CategoryDTO getCategoryDtoById(Long id) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(getCategoryUrl(id));

        CategoryDTO categoryDTO = restTemplate.getForObject(uriComponentsBuilder.toUriString(),CategoryDTO.class);
        return categoryDTO;
    }

    private String getCategoryUrl(Long id) {
        return this.api_category_url + "/" + id;
    }
}
