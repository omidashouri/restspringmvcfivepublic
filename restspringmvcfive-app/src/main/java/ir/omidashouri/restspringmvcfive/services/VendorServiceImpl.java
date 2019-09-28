package ir.omidashouri.restspringmvcfive.services;

import ir.omidashouri.restspringmvcfive.controllers.v1.VendorController;
import ir.omidashouri.restspringmvcfive.domain.Vendor;
import ir.omidashouri.restspringmvcfive.mapper.VendorMapper;
import ir.omidashouri.restspringmvcfive.model.VendorDTO;
import ir.omidashouri.restspringmvcfive.model.VendorListDTO;
import ir.omidashouri.restspringmvcfive.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }



    @Override
    public VendorListDTO getAllVendors() {

        List<VendorDTO> vendorDTOS =  vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
            return saveAndReturnDTO(vendorMapper.VendorDTOtoVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendorToSave = vendorMapper.VendorDTOtoVendor(vendorDTO);
        vendorToSave.setId(id);
        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
//                    if there is more property add it here

                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }
                    return saveAndReturnDTO(vendor);

                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL+"/"+id;
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor){

        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }
}
