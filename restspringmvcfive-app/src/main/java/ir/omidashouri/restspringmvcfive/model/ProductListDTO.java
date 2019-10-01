package ir.omidashouri.restspringmvcfive.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {

    @JsonProperty("products")
    private List<ProductDTO> productsDTO;

}
