package com.novi.cabforyou.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novi.cabforyou.models.Customer;
import com.novi.cabforyou.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    CustomerService customerService;

    Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setUsername("testUser");
        customer.setAddress("TestAddress 1, TestCity");

        customer.setFirstName("Petra");
        customer.setLastName("Peters");
    }

    @Test
    public void getCustomersReturnsStatusOk() throws Exception {
        mvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }

    @Test
    public void postCustomerReturnsStatusCreated() throws Exception {

        Customer newCustomer = new Customer();
        newCustomer.setUsername("ClientCabOne");
        newCustomer.setAddress("Nieuw Adresstraat 1, Amsterdam");
        newCustomer.setFirstName("Hans");
        newCustomer.setLastName("de Lange");

        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(newCustomer);

        mvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void getCustomerWithIdReturnsStatusOk() throws Exception {
        String username = "cabclientone";
        Mockito
                .when(customerService.getCustomerByUsername(username)).thenReturn(Optional.of(customer));
        mvc.perform(get("/customers/cabclientone", username))
                .andExpect(status().isOk());
    }
}