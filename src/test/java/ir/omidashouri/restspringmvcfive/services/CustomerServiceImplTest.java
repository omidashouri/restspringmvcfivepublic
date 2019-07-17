package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.mapper.CustomerMapper;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import ir.omidashouri.restspringmvcfive.repositories.CustomerRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {

    CustomerServiceImpl customerService;
    Customer customer;
    List<Customer> customerData;
    CustomerMapper customerMapper;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerMapper = CustomerMapper.INSTANCE;

        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
        customer = new Customer();
        customerData = new ArrayList<>();

    }

    @Test
    public void findCustomerById() {
    }

    @Test
    public void findAllCustomers() {

        customerData.add(customer);

        Mockito.when(customerService.findAllCustomers()).thenReturn(customerData);

        List<Customer> customers = customerService.findAllCustomers();

        assertEquals(customers.size(),1);
        Mockito.verify(customerRepository,Mockito.times(1)).findAll();


    }

    @Test
    public void saveCustomer() {
    }

    @Test
    public void getCustomerDtoById() {

        //        given
        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("omid1");
        customer.setLastName("ashouri1");

        Mockito.when(customerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(customer));

//        when
        CustomerDTO customerDTO = customerService.getCustomerDtoById(1L);

//        then
        assertEquals("omid1",customerDTO.getFirstName());
    }


    @Test
    public void createNewCustomer() {

//        given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("omid1");
        customerDTO.setLastName("ashouri1");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        Mockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(savedCustomer);

//        when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

//        then
        assertEquals(customerDTO.getFirstName(),savedDto.getFirstName());
        assertEquals("/api/v1/customers/1",savedDto.getCustomerUrl());
    }


    @Test
    public void saveCustomerByDTO() {

//        given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("omid");
        customerDTO.setLastName("ashouri");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        Mockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(savedCustomer);

//        when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L,customerDTO);

//        then
        assertEquals(customerDTO.getFirstName(),savedDto.getFirstName());
        assertEquals("/api/v1/customers/1",savedDto.getCustomerUrl());
    }


}