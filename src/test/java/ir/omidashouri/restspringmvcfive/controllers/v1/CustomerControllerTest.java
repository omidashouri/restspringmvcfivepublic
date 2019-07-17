package ir.omidashouri.restspringmvcfive.controllers.v1;

import ir.omidashouri.restspringmvcfive.mapper.CustomerMapper;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import ir.omidashouri.restspringmvcfive.repositories.CustomerRepository;
import ir.omidashouri.restspringmvcfive.services.CustomerService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    CustomerMapper customerMapper;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        customerMapper = CustomerMapper.INSTANCE;

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getListOfCustomersDto() throws Exception{

//        given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("omid1");
        customerDTO1.setLastName("ashouri1");
        customerDTO1.setCustomerUrl("/api/v1/customers/1");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("omid2");
        customerDTO2.setLastName("ashouri2");
        customerDTO2.setCustomerUrl("/api/v1/customers/2");

//        then
        Mockito.when(customerService.getAllCustomersDto()).thenReturn(Arrays.asList(customerDTO1,customerDTO2));

//        result
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));
    }

    @Test
    public void getCustomerDtoById() throws Exception{

//        given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("omid1");
        customerDTO.setLastName("ashouri1");
        customerDTO.setCustomerUrl("/api/v1/customers/1");

//        when
        Mockito.when(customerService.getCustomerDtoById(ArgumentMatchers.anyLong())).thenReturn(customerDTO);

//        then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.equalTo("omid1")));
    }
}