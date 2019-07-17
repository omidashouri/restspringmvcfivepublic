package ir.omidashouri.restspringmvcfive.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {

    @JsonProperty("customers")
    private List<CustomerDTO> customersDTO;
}
