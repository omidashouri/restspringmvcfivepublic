package ir.omidashouri.restspringmvcfive.controllers;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerControllerTest {

    Customer customer;


    @Before
    public void setUp(){
        customer =new Customer();
    }

    @Test
    public void getCustomerById() {
        customer.setId(1L);

    }
}