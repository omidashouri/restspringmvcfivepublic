package ir.omidashouri.restspringmvcfive.model;

import ir.omidashouri.restspringmvcfive.domain.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorListDTO {

    private List<Vendor> vendors;
}
