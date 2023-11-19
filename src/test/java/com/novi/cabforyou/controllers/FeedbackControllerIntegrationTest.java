package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.CustomerDto;
import com.novi.cabforyou.dtos.FeedbackDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@WithMockUser(username = "user1", roles = {"USER"})
public class FeedbackControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllFeedbacks() throws Exception {
        mockMvc.perform(get("/feedbacks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void testGetFeedback() throws Exception {
        int id = 5001; // Replace with a valid id
        mockMvc.perform(get("/feedbacks/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedbackId", is(id)));
    }

    @Test
    public void testAddFeedback() throws Exception {
        FeedbackDto newFeedback = new FeedbackDto();
        newFeedback.setRating(5d);
        newFeedback.setFeedback("Feedback");
        newFeedback.setSubmitDate(LocalDate.now());
        CustomerDto customer = new CustomerDto();
        customer.setUsername("cabclientone");
        newFeedback.setCustomer(customer);

        mockMvc.perform(post("/feedbacks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newFeedback)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateFeedback() throws Exception {
        long id = 5001;
        FeedbackDto updatedFeedback = new FeedbackDto();
        updatedFeedback.setRating(5d);
        updatedFeedback.setFeedback("Feedback");
        updatedFeedback.setSubmitDate(LocalDate.now());
        CustomerDto customer = new CustomerDto();
        updatedFeedback.setCustomer(customer);
        customer.setUsername("cabclientone");
        mockMvc.perform(put("/feedbacks/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedFeedback)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteFeedback() throws Exception {
        long id = 5001;
        mockMvc.perform(delete("/feedbacks/" + id))
                .andExpect(status().isNoContent());
    }
}
