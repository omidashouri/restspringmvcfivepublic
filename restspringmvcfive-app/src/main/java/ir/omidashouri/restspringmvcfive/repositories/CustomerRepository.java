package ir.omidashouri.restspringmvcfive.repositories;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
