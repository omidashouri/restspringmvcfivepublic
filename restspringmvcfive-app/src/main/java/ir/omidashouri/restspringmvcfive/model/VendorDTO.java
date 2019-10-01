package ir.omidashouri.restspringmvcfive.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    public VendorDTO() {
    }

    public VendorDTO(String name, String vendorUrl) {
        this.name = name;
        this.vendorUrl = vendorUrl;
    }

    public VendorDTO(Long id, String name, String vendorUrl) {
        this.id = id;
        this.name = name;
        this.vendorUrl = vendorUrl;
    }

    private Long id;

    @ApiModelProperty(value = "Name of the Vendor",required = true)
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;
}
