package ir.omidashouri.restspringmvcfive.mapper;

import ir.omidashouri.restspringmvcfive.domain.Vendor;
import ir.omidashouri.restspringmvcfive.model.VendorDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "omidashouri";



     VendorMapper vendorMapper;
     Vendor vendor;
     VendorDTO vendorDTO;


    @Before
    public void setUp() throws Exception {

        vendorMapper = VendorMapper.INSTANCE;

        vendor = new Vendor();

        vendorDTO = new VendorDTO();

    }

    @Test
    public void vendorToVendorDTO() {

//        given
        vendor.setName(NAME);

//        when
        vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

//        then
        assertEquals(NAME,vendorDTO.getName());

    }

    @Test
    public void vendorDTOtoVendor() {

//        given
        vendorDTO.setName(NAME);

//        when
        vendor = vendorMapper.VendorDTOtoVendor(vendorDTO);

//        then
        assertEquals(NAME,vendor.getName());

    }
}