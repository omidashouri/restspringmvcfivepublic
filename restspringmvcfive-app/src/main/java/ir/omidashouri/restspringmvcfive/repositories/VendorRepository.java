package ir.omidashouri.restspringmvcfive.repositories;

import ir.omidashouri.restspringmvcfive.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
}
