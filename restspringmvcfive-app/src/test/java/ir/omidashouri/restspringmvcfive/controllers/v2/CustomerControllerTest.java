package ir.omidashouri.restspringmvcfive.controllers.v2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.omidashouri.restspringmvcfive.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CustomerControllerTest {

    public static final String API_ROOT = "https://api.predic8.de:443/shop";

    @Mock @Qualifier("customerServiceImplClient")
    CustomerService customerService;

    @InjectMocks @Qualifier("customerControllerV2")
    CustomerController customerController;

    MockMvc mockMvc;

    RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        restTemplate = new RestTemplate();
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/")
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("index"));

    }

    @Test
    public void getCustomersClient() throws IOException {
        String apiUrl = API_ROOT + "/customers/";
        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);
        System.out.println("************Response****************");
        System.out.println(jsonNode.toString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonNode.toString());

        // When
        JsonNode jsonNode1 = actualObj.get("customers");
//        assertThat(jsonNode1.textValue(), equalTo("v1"));
    }

    @Test
    public void formPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/customers/")
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("customerlist"))
                .andExpect(model().attributeExists("customers"))
        ;
    }
}