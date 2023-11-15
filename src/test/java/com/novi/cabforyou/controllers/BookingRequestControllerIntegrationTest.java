package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.models.BookingStatus;
import com.novi.cabforyou.models.CarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookingRequestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllBookings() throws Exception {
        mockMvc.perform(get("/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    @Test
    public void testGetBooking() throws Exception {
        int id = 1001;
        mockMvc.perform(get("/bookings/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookingId", is(id)));
    }

    @Test
    public void testAddBooking() throws Exception {
        BookingRequestDto newBooking = new BookingRequestDto();
        newBooking.setCarType(CarType.WAGON);
        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newBooking)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateBooking() throws Exception {
        long id = 1001;
        BookingRequestDto updatedBooking = new BookingRequestDto();
        updatedBooking.setCarType(CarType.SEDAN);
        mockMvc.perform(put("/bookings/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedBooking)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testPatchBookingRequest() throws Exception {
        long id = 1001;
        BookingRequestDto patchBooking = new BookingRequestDto();
        mockMvc.perform(patch("/bookings/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patchBooking)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBooking() throws Exception {
        long id = 1001;
        mockMvc.perform(delete("/bookings/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetBookingRequestsByStatus() throws Exception {
        BookingStatus status = BookingStatus.REQUEST;
        mockMvc.perform(get("/bookings/status/" + status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }
}
