package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.CustomerDto;
import com.novi.cabforyou.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> dtos = customerService.getAllCustomers();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{username}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("username") String username) {
        CustomerDto dto = customerService.getCustomer(username);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto dto) {
        CustomerDto dto1 = customerService.addCustomer(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("username") String username, @RequestBody CustomerDto dto) {
        customerService.updateCustomer(username, dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{username}")
    public ResponseEntity<CustomerDto> patchOneCustomer(@PathVariable("username") String username, @RequestBody CustomerDto updatedCustomerDto) {
        CustomerDto updatedDto = customerService.patchOneCustomer(username, updatedCustomerDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("username") String username) {
        customerService.deleteCustomer(username);
        return ResponseEntity.noContent().build();
    }

}
