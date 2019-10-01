package ir.omidashouri.restspringmvcfive.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category_url")
    private String categoryUrl;

    @JsonProperty("products")
    private List<ProductDTO> products;

//    add for test
    private ProductListDTO productListDTO;

//      add for test
    public void setProductListDTO(ProductListDTO productListDTO) {
        this.productListDTO.setProductsDTO(products);
    }
}
