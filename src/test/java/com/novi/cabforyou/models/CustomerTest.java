package com.novi.cabforyou.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();
        customer.setAddress("Adresstraat 50");
        assertEquals("Adresstraat 50", customer.getAddress());
    }

    @Test
    void emptyMyBookingRequestsReturnsEmptyArrayList() {
        Customer customer = new Customer();
        var expected = new ArrayList<>();
        var actual = customer.getBookingRequests();
        assertEquals(expected, actual);
    }

}