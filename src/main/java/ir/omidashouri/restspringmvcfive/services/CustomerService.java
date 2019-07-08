package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.domain.Customer;

import java.util.List;

public interface CustomerService {

    Customer findCustomerById(Long id);

    List<Customer> findAllCustomers();


}
