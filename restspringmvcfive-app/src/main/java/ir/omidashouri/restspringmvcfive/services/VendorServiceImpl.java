package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.mapper.VendorMapper;
import ir.omidashouri.restspringmvcfive.model.VendorDTO;
import ir.omidashouri.restspringmvcfive.model.VendorListDTO;
import ir.omidashouri.restspringmvcfive.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    private VendorMapper vendorMapper;
    private VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return  vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO getAllVendors() {
        return null;
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return null;
    }

    @Override
    public void deleteVendorById(Long id) {

    }
}
