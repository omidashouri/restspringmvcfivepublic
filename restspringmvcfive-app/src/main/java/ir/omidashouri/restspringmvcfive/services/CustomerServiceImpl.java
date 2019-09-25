package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.controllers.v1.CustomerController;
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
                    CustomerDTO customerDto = customerMapper.customerToCustomerDTO(cust);
                    customerDto.setCustomerUrl(getCustomerUrl(cust.getId()));
                    return customerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerDtoById(Long id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setCustomerUrl(getCustomerUrl(id));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
//        add implemented exception class here
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        Customer savedCustomer =  customerRepository.save(customer);
        CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnCustomerDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnCustomerDTO;
    }

    private CustomerDTO saveAndReturnDTO(Customer customer){

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);

        returnDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnDTO;
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return this.saveAndReturnDTO(customer);
    }

    // patch mean just update changed value and leave other alone
    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {

        return customerRepository.findById(id).map(customer -> {

            if(null!=customerDTO.getFirstName()){
                customer.setFirstName(customerDTO.getFirstName());
            }
            if(null!=customerDTO.getLastName()){
                customer.setLastName(customerDTO.getLastName());
            }

            CustomerDTO returnCustomerDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));

            returnCustomerDTO.setCustomerUrl(getCustomerUrl(id));

            return returnCustomerDTO;
        }).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);

    }

    private String getCustomerUrl(Long id){
        return CustomerController.BASE_URL+"/"+id;
    }
}
