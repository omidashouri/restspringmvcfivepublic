package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.mapper.CustomerMapper;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import ir.omidashouri.restspringmvcfive.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomersDto() {
        return customerRepository
                .findAll()
                .stream()
                .map(cust->{
                    CustomerDTO customerDto = customerMapper.cutomerToCustomerDTO(cust);
                    customerDto.setCustomerUrl("/api/v1/customers/"+cust.getId());
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerDtoById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::cutomerToCustomerDTO)
                .get();
    }
}
