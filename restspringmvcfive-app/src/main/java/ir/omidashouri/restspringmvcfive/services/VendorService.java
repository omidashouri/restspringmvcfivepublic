package ir.omidashouri.restspringmvcfive.services;


import ir.omidashouri.restspringmvcfive.model.VendorDTO;
import ir.omidashouri.restspringmvcfive.model.VendorListDTO;

public interface VendorService {

    VendorDTO getVendorById(Long id);

    VendorListDTO getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id,VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);

}
