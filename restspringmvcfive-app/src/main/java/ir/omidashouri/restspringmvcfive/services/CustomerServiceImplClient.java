package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import ir.omidashouri.restspringmvcfive.model.CustomerListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class CustomerServiceImplClient implements CustomerService {

    private RestTemplate restTemplate;

    private final String api_customer_url;

    @Autowired
    public CustomerServiceImplClient(RestTemplate restTemplate,@Value("${api.customer.url}") String api_customer_url) {
        this.restTemplate = restTemplate;
        this.api_customer_url = api_customer_url;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return null;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomersDto() {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(api_customer_url);

        CustomerListDTO customerListDTO = restTemplate.getForObject(uriComponentsBuilder.toUriString(),CustomerListDTO.class);
        return customerListDTO.getCustomersDTO();
    }

    @Override
    public CustomerDTO getCustomerDtoById(Long id) {
        return null;
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return null;
    }

    @Override
    public void deleteCustomerById(Long id) {

    }
}
