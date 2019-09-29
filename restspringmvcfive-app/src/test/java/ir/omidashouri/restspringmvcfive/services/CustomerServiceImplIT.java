package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.bootstrap.BootStrapData;
import ir.omidashouri.restspringmvcfive.bootstrap.BootStrapVendor;
import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.mapper.CustomerMapper;
import ir.omidashouri.restspringmvcfive.mapper.VendorMapper;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import ir.omidashouri.restspringmvcfive.repositories.CategoryRepository;
import ir.omidashouri.restspringmvcfive.repositories.CustomerRepository;
import ir.omidashouri.restspringmvcfive.repositories.VendorRepository;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class CustomerServiceImplIT {

//    this is Integration Test we have @DataJpaTest annotation

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    VendorService vendorService;

    @Before
    public void setup() throws Exception {

        log.info("Loading Customer Data");
        log.info(String.valueOf(customerRepository.findAll().size()));

    //  setup data for testing
        BootStrapData bootStrapData = new BootStrapData(customerRepository);
        bootStrapData.run(); //load Data

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);

        log.info("Loading Vendor Data");
        log.info(String.valueOf(vendorRepository.findAll().size()));

        BootStrapVendor bootStrapVendor = new BootStrapVendor(vendorRepository);
        bootStrapVendor.run();

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE,vendorRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception{

//        given
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        Assert.assertNotNull(originalCustomer);
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

//        when
        customerService.patchCustomer(id,customerDTO);

//        then

        Customer updatedCustomer = customerRepository.findById(id).get();

        TestCase.assertNotNull(updatedCustomer);
        Assert.assertEquals(updatedName,updatedCustomer.getFirstName());
        Assert.assertThat(originalFirstName, Matchers.not(Matchers.equalTo(updatedCustomer.getFirstName())));
        Assert.assertThat(originalLastName, Matchers.equalTo(updatedCustomer.getLastName()));

    }

    public void patchCustomerUpdateLastName() throws Exception{

    }

    private long getCustomerIdValue(){

        List<Customer> customers = customerRepository.findAll();

        log.info("Customers Found: "+customers.size());

        return customers.get(0).getId();
    }



    /*
    @RunWith(MockitoJUnitRunner.class) VS @RunWith(SpringRunner.class)

    The SpringRunner provides support for loading a Spring ApplicationContext
    and having beans @Autowired into your test instance.
    Whereas, the MockitoJUnitRunner provides support for creating mocks and spies with Mockito.
    However, with JUnit 4, you can only use one Runner at a time.
    For example, you can use the Spring runner with the Mockito rule as follows.

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class MyTests {

        @Rule
        public MockitoRule rule = MockitoJUnit.rule();

        @Mock
        MyService myService;

        // ...
    }
     */
}
