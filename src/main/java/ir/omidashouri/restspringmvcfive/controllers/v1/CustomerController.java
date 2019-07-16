package ir.omidashouri.restspringmvcfive.controllers.v1;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import ir.omidashouri.restspringmvcfive.model.CustomerListDTO;
import ir.omidashouri.restspringmvcfive.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @GetMapping
    List<Customer> getAllCustomers(){
        return customerService.findAllCustomers();
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomersDto(){
        return new ResponseEntity<CustomerListDTO>(new CustomerListDTO(customerService.getAllCustomersDto()),
                HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CustomerDTO> getCustomerDtoById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerDtoById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }
}


//32:00