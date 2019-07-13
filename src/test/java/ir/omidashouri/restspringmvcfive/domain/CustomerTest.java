package ir.omidashouri.restspringmvcfive.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {


    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        customer.setId(idValue);
        assertEquals(idValue,customer.getId());
    }

    @Test
    public void getFirstName() {
    }

    @Test
    public void getLastName() {
    }
}