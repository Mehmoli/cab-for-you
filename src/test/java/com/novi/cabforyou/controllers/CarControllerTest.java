package com.novi.cabforyou.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novi.cabforyou.dtos.CarDto;
import com.novi.cabforyou.models.Car;
import com.novi.cabforyou.models.CarType;
import com.novi.cabforyou.repositories.CarRepository;
import com.novi.cabforyou.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CarController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockitoSettings(strictness = Strictness.LENIENT)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    Car car1;
    Car car2;
    CarDto carDto1;
    CarDto carDto2;
    CarDto carDto3;

    @BeforeEach
    void setUp() {
        car1 = new Car();
        car2 = new Car();
        carDto1 = new CarDto(1001L, "Mercedes", "E350D", 4, "SS-SS-11", CarType.SEDAN);
        carDto2 = new CarDto(1002L, "Mercedes", "E200D", 4, "WW-WW-22", CarType.WAGON);
        carDto3 = new CarDto(1003L, "Mercedes", "V-Klasse", 6, "MB-MB-33", CarType.MINIBUS);
    }

    @Test
    void testAddCar() throws Exception {
        given(carService.addCar(any(CarDto.class))).willReturn(carDto1);

        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carDto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("carId").value(1001L))
                .andExpect(jsonPath("make").value("Mercedes"))
                .andExpect(jsonPath("model").value("E350D"))
                .andExpect(jsonPath("availableSeats").value(4))
                .andExpect(jsonPath("licensePlate").value("SS-SS-11"))
                .andExpect(jsonPath("carType").value("SEDAN"));
    }

    @Test
    void testGetAllCars() throws Exception {
        given(carService.getAllCars()).willReturn(List.of(carDto1, carDto2));

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].carId").value(1001L))
                .andExpect(jsonPath("$[0].make").value("Mercedes"))
                .andExpect(jsonPath("$[0].model").value("E350D"))
                .andExpect(jsonPath("$[0].availableSeats").value(4))
                .andExpect(jsonPath("$[0].licensePlate").value("SS-SS-11"))
                .andExpect(jsonPath("$[0].carType").value("SEDAN"))

                .andExpect(jsonPath("$[1].carId").value(1002L))
                .andExpect(jsonPath("$[1].make").value("Mercedes"))
                .andExpect(jsonPath("$[1].model").value("E200D"))
                .andExpect(jsonPath("$[1].availableSeats").value(4))
                .andExpect(jsonPath("$[1].licensePlate").value("WW-WW-22"))
                .andExpect(jsonPath("$[1].carType").value("WAGON"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}