package ir.omidashouri.restspringmvcfive.controllers.v2;

import ir.omidashouri.restspringmvcfive.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller("customerControllerV2")
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v2";

    private CustomerService customerService;

    @Autowired
    public CustomerController(@Qualifier("customerServiceImplClient") CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"", "/", "/index"})
    public String index(){
        return "index";
    }

    @PostMapping("/customers")
    public String formPost(Model model){
//        MultiValueMap map = serverWebExchange.getFormData().block();

        model.addAttribute("customers",customerService.getAllCustomersDto());
        return "customerlist";
    }



}
