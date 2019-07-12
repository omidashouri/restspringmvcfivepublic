package ir.omidashouri.restspringmvcfive.bootstrap;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.domain.FakeCustomer;
import ir.omidashouri.restspringmvcfive.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootStrapData implements CommandLineRunner {


    private final CustomerRepository customerRepository;
    private Customer customer1;

    @Value("${fake.customer.first.name}")
    private String fakeCustomerFirstName;

    @Value("${fake.customer.last.name}")
    private String fakeCustomerLastName;

    @Autowired
    private FakeCustomer fakeCustomer = new FakeCustomer();

    @Autowired
    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customer1 = new Customer();
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Loading Customer Data");

        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customerRepository.save(customer1);


        Customer customer2 = new Customer();
        customer2.setFirstName("San");
        customer2.setLastName("Axe");
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Fiona");
        customer3.setLastName("Gilendrn");
        customerRepository.save(customer3);

        Customer customer4 = new Customer();
        customer4.setFirstName(fakeCustomer.getFirstName());
        customer4.setLastName(fakeCustomer.getLastName());
        customerRepository.save(customer4);

        log.info("Customers saved: " + customerRepository.count());
    }

}
