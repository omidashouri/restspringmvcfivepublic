package ir.omidashouri.restspringmvcfive.mapper;

import ir.omidashouri.restspringmvcfive.domain.Customer;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
     CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

     @Mappings({
             @Mapping(source = "firstName",target = "firstName"),
             @Mapping(source = "lastName",target = "lastName")
     })
     CustomerDTO cutomerToCustomerDTO(Customer customer);
}
