package ir.omidashouri.restspringmvcfive.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySources({
        @PropertySource("classpath:fakecustomer.properties")
        })
public class PropertyConfig {


    @Value("${fake.customer.first.name}")
    String firstNameConf;

    @Value("${fake.customer.last.name}")
    String lastNameConf;

/*    @Bean
    public FakeCustomer fakeCustomer(){
        FakeCustomer fakeCustomer = new FakeCustomer();
        fakeCustomer.setFirstName(firstNameConf);
        fakeCustomer.setLastName(lastNameConf);
        return fakeCustomer;
    }*/


    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }
}
