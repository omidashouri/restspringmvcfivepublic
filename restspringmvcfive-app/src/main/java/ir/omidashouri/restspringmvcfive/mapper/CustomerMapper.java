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
             @Mapping(source = "id",target = "id"),
             @Mapping(source = "firstName",target = "firstName"),
             @Mapping(source = "lastName",target = "lastName"),
             @Mapping(source = "description",target = "customerUrl")
     })
     CustomerDTO customerToCustomerDTO(Customer customer);

     @Mappings({
             @Mapping(source = "id",target = "id"),
             @Mapping(source = "firstName",target = "firstName"),
             @Mapping(source = "lastName",target = "lastName"),
             @Mapping(source = "customerUrl",target = "description")
     })
     Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
