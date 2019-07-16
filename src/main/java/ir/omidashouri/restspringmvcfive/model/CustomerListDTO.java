package ir.omidashouri.restspringmvcfive.model;

import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {

    private List<CustomerDTO> customersDTO;
}
