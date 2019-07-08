package ir.omidashouri.restspringmvcfive.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_generator")
    @SequenceGenerator(name="customer_generator", sequenceName = "customer_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private String lastName;



//    16:14


}
