package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.CustomerDto;
import com.novi.cabforyou.exceptions.UsernameNotFoundException;
import com.novi.cabforyou.models.Customer;
import com.novi.cabforyou.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;
    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDto = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        for (Customer cu: customers){
            customerDto.add(transferToCustomerDto(cu));
        }
        return customerDto;
    }

    public CustomerDto getCustomer(String username) {
        CustomerDto dto = new CustomerDto();
        Optional<Customer> customer = customerRepository.findByUsername(username);
        if (customer.isPresent()) {
            dto = transferToCustomerDto(customer.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {

        Customer customer = transferToCustomer(customerDto);
        customerRepository.save(customer);
        return customerDto;
    }

    public void updateCustomer(String username, CustomerDto newCustomer) {
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setEmail(newCustomer.getEmail());
            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setAddress(newCustomer.getAddress());

            customerRepository.save(customer);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Transactional
    public CustomerDto patchOneCustomer(String username, CustomerDto updatedCustomerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            if (updatedCustomerDto.getEmail() != null) {
                existingCustomer.setEmail(updatedCustomerDto.getEmail());
            }
            if (updatedCustomerDto.getAddress() != null) {
                existingCustomer.setAddress(updatedCustomerDto.getAddress());
            }
            if (updatedCustomerDto.getPhoneNumber() != null) {
                existingCustomer.setPhoneNumber(updatedCustomerDto.getPhoneNumber());
            }

            customerRepository.save(existingCustomer);

            return transferToCustomerDto(existingCustomer);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Transactional
    public void deleteCustomer(String username) {
        customerRepository.deleteByUsername(username);
    }

    public CustomerDto transferToCustomerDto(Customer cu) {

        var dto = new CustomerDto();

        dto.username =cu.getUsername();
        dto.password = cu.getPassword();
        dto.firstName = cu.getFirstName();
        dto.lastName = cu.getLastName();
        dto.email = cu.getEmail();
        dto.phoneNumber = cu.getPhoneNumber();
        dto.address = cu.getAddress();
        dto.authorities = cu.getAuthorities();

        return dto;
    }

    private Customer transferToCustomer(CustomerDto customerDto) {

        Customer customer = new Customer();

        customer.setUsername(customerDto.getUsername());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setAuthorities(customerDto.getAuthorities());

        return customer;
    }


    public Object getCustomerByUsername(String username) {
        return null;
    }
}
