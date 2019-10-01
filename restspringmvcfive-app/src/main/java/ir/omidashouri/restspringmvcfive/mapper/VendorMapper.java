package ir.omidashouri.restspringmvcfive.mapper;

import ir.omidashouri.restspringmvcfive.domain.Vendor;
import ir.omidashouri.restspringmvcfive.model.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name")
    })
    VendorDTO vendorToVendorDTO(Vendor vendor);

    @Mappings({
            @Mapping(source = "id",target = "id"),
            @Mapping(source = "name",target = "name")
    })
    Vendor VendorDTOtoVendor(VendorDTO vendorDTO);
}
