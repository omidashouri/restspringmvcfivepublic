package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplClientTest {

    @Autowired
    @Qualifier("customerServiceImplClient")
    CustomerService customerService;



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllCustomersDto() {
        List<CustomerDTO>  customersDto = customerService.getAllCustomersDto();
        System.out.println("**********************************");
        customersDto.stream().forEach(e->{
            System.out.println(e.getCustomerUrl());
        });
        System.out.println(String.valueOf(customersDto.size()));
    }
}