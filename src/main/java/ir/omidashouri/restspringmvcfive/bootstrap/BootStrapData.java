package ir.omidashouri.restspringmvcfive.bootstrap;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {


    private final CustomerRepository customerRepository;

    @Autowired
    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading Customer Data");

        Customer customer1 = new Customer();
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

        System.out.println("Customers saved: " + customerRepository.count());

    }



//    22:50

}
