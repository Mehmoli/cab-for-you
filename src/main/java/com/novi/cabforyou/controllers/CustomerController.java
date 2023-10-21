package com.novi.cabforyou.controllers;


import com.novi.cabforyou.dtos.CustomerDto;
import com.novi.cabforyou.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabforyou/customers")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {

        List<CustomerDto> dtos = customerService.getAllCustomers();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") long id) {

        CustomerDto dto = customerService.getCustomer(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto dto) {
        CustomerDto dto1 = customerService.addCustomer(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") String username, @RequestBody CustomerDto dto) {

        customerService.updateCustomer(username, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") String username) {
        customerService.deleteCustomer(username);
        return ResponseEntity.noContent().build();
    }
}
