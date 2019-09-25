package ir.omidashouri.restspringmvcfive.controllers.v1;

import ir.omidashouri.restspringmvcfive.controllers.RestResponseEntityExceptionHandler;
import ir.omidashouri.restspringmvcfive.mapper.CustomerMapper;
import ir.omidashouri.restspringmvcfive.model.CustomerDTO;
import ir.omidashouri.restspringmvcfive.services.CustomerService;
import ir.omidashouri.restspringmvcfive.services.ResourceNotFoundException;
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

//        add implemented exception class here

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getListOfCustomersDto() throws Exception{

//        given
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName("omid1");
        customerDTO1.setLastName("ashouri1");
        customerDTO1.setCustomerUrl(CustomerController.BASE_URL+"/1");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("omid2");
        customerDTO2.setLastName("ashouri2");
        customerDTO2.setCustomerUrl(CustomerController.BASE_URL+"/2");

//        then
        Mockito.when(customerService.getAllCustomersDto()).thenReturn(Arrays.asList(customerDTO1,customerDTO2));

//        result
        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
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
        customerDTO.setCustomerUrl(CustomerController.BASE_URL+"/1");

//        when
        Mockito.when(customerService.getCustomerDtoById(ArgumentMatchers.anyLong())).thenReturn(customerDTO);

//        then
        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", CoreMatchers.equalTo("omid1")));
    }

    @Test
    public void createNewCustomerDto() throws Exception {

//        given
        CustomerDTO argumentDto = new CustomerDTO();
        argumentDto.setFirstName("omid1");
        argumentDto.setLastName("ashouri1");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName(argumentDto.getFirstName());
        returnDto.setLastName(argumentDto.getLastName());
        returnDto.setCustomerUrl(CustomerController.BASE_URL+"/1");

//        when
        Mockito.when(customerService.createNewCustomer(argumentDto)).thenReturn(returnDto);

//        then
        mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(argumentDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname",Matchers.equalTo("omid1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer_url", Matchers.equalTo(CustomerController.BASE_URL+"/1")))
                .andReturn().getResponse().getContentAsString();

//        Use for Debug
/*        String responseDebug = mockMvc.perform(MockMvcRequestBuilders.post(CustomerController.BASE_URL)
                            .accept(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(AbstractRestControllerTest.asJsonString(argumentDto)))
                            .andReturn().getResponse().getContentAsString();
        System.out.println(responseDebug);*/
    }

    @Test
    public void testUpdateCustomer() throws Exception{

//        given
        CustomerDTO argumentDto = new CustomerDTO();
        argumentDto.setFirstName("omid");
        argumentDto.setLastName("ashouri");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstName(argumentDto.getFirstName());
        returnDto.setLastName(argumentDto.getLastName());
        returnDto.setCustomerUrl(CustomerController.BASE_URL+"/1");

//        when
        Mockito.when(customerService.saveCustomerByDTO(ArgumentMatchers.anyLong(),ArgumentMatchers.any(CustomerDTO.class)))
                .thenReturn(returnDto);

//        then
        mockMvc.perform(MockMvcRequestBuilders.put(CustomerController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(AbstractRestControllerTest.asJsonString(argumentDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname",Matchers.equalTo("omid")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname",Matchers.equalTo("ashouri")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer_url",Matchers.equalTo(CustomerController.BASE_URL+"/1")))
                .andReturn().getResponse().getContentAsString();
    }


    @Test
    public void testPatchCustomer() throws Exception {

//        given
    CustomerDTO givenDto = new CustomerDTO();
    givenDto.setFirstName("givenOmid1");

    CustomerDTO returnDto = new CustomerDTO();
    returnDto.setFirstName(givenDto.getFirstName());
    returnDto.setLastName("ashouri1");
    returnDto.setCustomerUrl(CustomerController.BASE_URL+"/1");

//        when
    Mockito.when(customerService.patchCustomer(ArgumentMatchers.anyLong(),ArgumentMatchers.any(CustomerDTO.class)))
            .thenReturn(returnDto);

//        then
    mockMvc.perform(MockMvcRequestBuilders.patch(CustomerController.BASE_URL+"/1")
            .accept(MediaType.APPLICATION_JSON)
    .contentType(MediaType.APPLICATION_JSON)
    .content(AbstractRestControllerTest.asJsonString(givenDto)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.equalTo("givenOmid1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.equalTo("ashouri1")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.customer_url",Matchers.equalTo(CustomerController.BASE_URL+"/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete(CustomerController.BASE_URL+"/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

//        verify if this method is running
        Mockito.verify(customerService).deleteCustomerById(ArgumentMatchers.anyLong());
    }

    @Test
    public void testGetByIdNotFound() throws Exception{

        Mockito.when(customerService.getCustomerDtoById(ArgumentMatchers.anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(CustomerController.BASE_URL+"/55")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}