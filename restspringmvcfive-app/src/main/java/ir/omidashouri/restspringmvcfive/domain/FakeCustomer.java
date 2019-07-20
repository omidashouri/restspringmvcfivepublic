package ir.omidashouri.restspringmvcfive.domain;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FakeCustomer {


    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    @Value("${fake.customer.first.name}")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Value("${fake.customer.last.name}")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
