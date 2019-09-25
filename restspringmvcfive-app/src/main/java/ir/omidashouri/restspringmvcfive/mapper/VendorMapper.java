package ir.omidashouri.restspringmvcfive.mapper;

import ir.omidashouri.restspringmvcfive.domain.Vendor;
import ir.omidashouri.restspringmvcfive.model.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor VendorDTOtoVendor(VendorDTO vendorDTO);
}
