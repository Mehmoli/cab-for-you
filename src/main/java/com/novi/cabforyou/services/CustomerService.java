package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.CustomerDto;
import com.novi.cabforyou.models.Customer;
import com.novi.cabforyou.models.User;
import com.novi.cabforyou.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getAllCustomers() {

        List<CustomerDto> customerDto = new ArrayList<>();
        List<User> customers = customerRepository.findAll();
        for (User cu: customers){
            customerDto.add(transferToCustomerDto((Customer) cu));
        }
        return customerDto;

    }

    public CustomerDto getCustomer(long id) {
        return null; //Todo
    }

    public CustomerDto addCustomer(CustomerDto dto) {
        return null; //Todo
    }

    public void updateCustomer(String username, CustomerDto dto) {
    }

    public void deleteCustomer(String username) {
        customerRepository.deleteById(username);
    }



    private CustomerDto transferToCustomerDto(Customer cu) {

        var dto = new CustomerDto();

        dto.username =cu.getUsername();
        dto.address = cu.getAddress();
        dto.customerPhone = cu.getCustomerPhone();
        dto.phoneNumber = cu.getPhoneNumber();
        dto.email = cu.getEmail();
        dto.firstName = cu.getFirstName();
        dto.lastName = cu.getLastName();

        return dto;
    }



}
