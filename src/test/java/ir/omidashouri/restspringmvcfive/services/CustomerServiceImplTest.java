package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerServiceImplTest {

    CustomerServiceImpl customerService;
    Customer customer;
    List<Customer> customerData;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository);
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
}