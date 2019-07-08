package ir.omidashouri.restspringmvcfive.bootstrap;

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

    }



//    22:50

}
