package ir.omidashouri.restspringmvcfive.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_generator")
    @SequenceGenerator(name="category_generator", sequenceName = "category_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
}
