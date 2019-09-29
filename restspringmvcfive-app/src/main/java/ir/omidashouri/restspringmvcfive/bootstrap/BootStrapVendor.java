package ir.omidashouri.restspringmvcfive.bootstrap;

import ir.omidashouri.restspringmvcfive.domain.Vendor;
import ir.omidashouri.restspringmvcfive.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootStrapVendor implements CommandLineRunner {


    private final VendorRepository vendorRepository;

    @Autowired
    public BootStrapVendor(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadVendors();
    }


    private void loadVendors() {

        log.info("Loading Vendor Data");

        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor 1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor 2");
        vendorRepository.save(vendor2);

        log.info("Vendor Data Loaded = " + vendorRepository.count());

    }

}
