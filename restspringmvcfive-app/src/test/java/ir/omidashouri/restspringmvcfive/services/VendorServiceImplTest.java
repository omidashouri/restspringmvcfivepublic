package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.domain.Vendor;
import ir.omidashouri.restspringmvcfive.mapper.VendorMapper;
import ir.omidashouri.restspringmvcfive.model.VendorDTO;
import ir.omidashouri.restspringmvcfive.model.VendorListDTO;
import ir.omidashouri.restspringmvcfive.repositories.VendorRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class VendorServiceImplTest {

    public static final String NAME_1 = "Omid Ashouri";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "Omid Ashouri";
    public static final long ID_2 = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE,vendorRepository);
    }

    @Test
    public void getVendorById() {

//        given
        Vendor vendor = getVendor1();

//        Behavior-driven Development of Mockito
        given(vendorRepository.findById(ArgumentMatchers.anyLong())).willReturn(Optional.of(vendor));

//        when
        VendorDTO vendorDTO = vendorService.getVendorById(ID_1);

//        then
        BDDMockito.then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(vendorDTO.getName(),is(CoreMatchers.equalTo(NAME_1)));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getVendorByIdNotFound() throws Exception{

//        given
        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

//        when
        VendorDTO vendorDTO = vendorService.getVendorById(ID_1);

//        then
        BDDMockito.then(vendorRepository).should(times(1)).findById(anyLong());

    }


    @Test
    public void getAllVendors() throws Exception {
//        given
        List<Vendor> vendors = Arrays.asList(getVendor1(),getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

//        when
        VendorListDTO vendorListDTO = vendorService.getAllVendors();

//        then
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendors().size(),is(equalTo(2)));
    }

    @Test
    public void createNewVendor() {

//        given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).willReturn(vendor);

//        when
        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

//        then
        then(vendorRepository).should().save(ArgumentMatchers.any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(),containsString("1"));

    }

    @Test
    public void saveVendorByDTO() throws Exception {

//        given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.saveVendorByDTO(ID_1, vendorDTO);

        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(ArgumentMatchers.any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void patchVendor() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).willReturn(vendor);

        //when

        VendorDTO savedVendorDTO = vendorService.patchVendor(ID_1, vendorDTO);

        //then
        then(vendorRepository).should().save(ArgumentMatchers.any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));


    }

    @Test
    public void deleteVendorById() {

        //when
        vendorService.deleteVendorById(ID_1);

        //then
        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}