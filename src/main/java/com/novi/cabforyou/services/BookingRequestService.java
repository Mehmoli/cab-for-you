package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.BookingRequestDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.*;
import com.novi.cabforyou.repositories.BookingRequestRepository;
import com.novi.cabforyou.repositories.CustomerRepository;
import com.novi.cabforyou.repositories.PlannerRepository;
import com.novi.cabforyou.repositories.TripRepository;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingRequestService {

    private final BookingRequestRepository bookingRequestRepository;
    private final TripRepository tripRepository;

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    private final PlannerService plannerService;

    private final PlannerRepository plannerRepository;

    public BookingRequestService(BookingRequestRepository bookingRequestRepository, TripRepository tripRepository, CustomerService customerService, CustomerRepository customerRepository, PlannerService plannerService, PlannerRepository plannerRepository) {
        this.bookingRequestRepository = bookingRequestRepository;
        this.tripRepository = tripRepository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.plannerService = plannerService;
        this.plannerRepository = plannerRepository;
    }

    public List<BookingRequestDto> getAllBookings() {
        List<BookingRequestDto> bookingRequestDto = new ArrayList<>();
        List<BookingRequest> bookingRequests = bookingRequestRepository.findAll();
        for (BookingRequest b : bookingRequests) {
            bookingRequestDto.add(transferToBookingRequestDto(b));
        }
        return bookingRequestDto;
    }

    public BookingRequestDto getBooking(long id) {
        Optional<BookingRequest> booking = bookingRequestRepository.findById(id);
        if (booking.isPresent()) {
            return transferToBookingRequestDto(booking.get());
        } else {
            throw new RecordNotFoundException("No booking found!!!");
        }
    }

    public BookingRequestDto addBooking(BookingRequestDto bookingRequestDto) {
        BookingRequest bookingRequest = transferToBookingRequest(bookingRequestDto);
        if (bookingRequestDto.customer != null) {
            Optional<Customer> customerOpt = customerRepository.findByUsername(bookingRequestDto.customer.username);
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                bookingRequest.setCustomer(customer);
            }
        }

        if (bookingRequestDto.planner != null) {
            Optional<Planner> plannerOpt = plannerRepository.findByUsername(bookingRequestDto.planner.username);
            if (plannerOpt.isPresent()) {
                Planner planner = plannerOpt.get();
                bookingRequest.setPlanner(planner);
            }
        }

        CalculatedTripAndKmPriceSetter(bookingRequestDto, bookingRequest);
        bookingRequestRepository.save(bookingRequest);

        bookingRequestDto = transferToBookingRequestDto(bookingRequest);
        return bookingRequestDto;
    }

    public void updateBooking(Long id, BookingRequestDto newBooking) {
        if (!bookingRequestRepository.existsById(id)) throw new RecordNotFoundException("BookingRequest not found");
        BookingRequest bookingRequest = bookingRequestRepository.findById(id).get();

        CalculatedTripAndKmPriceSetter(newBooking, bookingRequest);

        bookingRequest.setTripDate(newBooking.getTripDate());
        bookingRequest.setTripTime(newBooking.getTripTime());
        bookingRequest.setFromAddress(newBooking.getFromAddress());
        bookingRequest.setToAddress(newBooking.getToAddress());
        bookingRequest.setNumberOfPeople(newBooking.getNumberOfPeople());
        bookingRequest.setDistanceInKm(newBooking.getDistanceInKm());
        bookingRequest.setCarType(newBooking.getCarType());
        bookingRequest.setBookingStatus(newBooking.getBookingStatus());
        bookingRequestRepository.save(bookingRequest);
    }

    private void CalculatedTripAndKmPriceSetter(BookingRequestDto newBooking, BookingRequest bookingRequest) {
        CarType selectedCarType = newBooking.getCarType();
        double kmPrice = getCorrectKmPrice(selectedCarType);
        newBooking.setKmPrice(kmPrice);
        bookingRequest.setKmPrice(kmPrice);

        double distanceInKM = newBooking.getDistanceInKm();

        double tripPrice = calculateTripPrice(selectedCarType, distanceInKM);
        newBooking.setTripPrice(tripPrice);
        bookingRequest.setTripPrice(tripPrice);
    }


    public BookingRequestDto patchBookingRequest(Long id, BookingRequestDto patchBookingRequest) {
        Optional<BookingRequest> optionalBookingRequest = bookingRequestRepository.findById(id);

        if (optionalBookingRequest.isPresent()) {
            BookingRequest existingBookingRequest = optionalBookingRequest.get();

            if (patchBookingRequest.getTrip() != null) {
                existingBookingRequest.setTrip(tripRepository.getReferenceById(patchBookingRequest.getTrip().getTripId()));
            }
            if (patchBookingRequest.getCustomer() != null) {
                Optional<Customer> customerOpt = customerRepository.findByUsername(patchBookingRequest.customer.username);
                if (customerOpt.isPresent()) {
                    Customer customer = customerOpt.get();
                    existingBookingRequest.setCustomer(customer);
                }
            }
            if (patchBookingRequest.getPlanner() != null) {
                Optional<Planner> plannerOpt = plannerRepository.findByUsername(patchBookingRequest.planner.username);
                if (plannerOpt.isPresent()) {
                    Planner planner = plannerOpt.get();
                    existingBookingRequest.setPlanner(planner);
                }
            }
            if (patchBookingRequest.getTripDate() != null) {
                existingBookingRequest.setTripDate(patchBookingRequest.getTripDate());
            }
            if (patchBookingRequest.getTripTime() != null) {
                existingBookingRequest.setTripTime(patchBookingRequest.getTripTime());
            }
            if (patchBookingRequest.getFromAddress() != null) {
                existingBookingRequest.setFromAddress(patchBookingRequest.getFromAddress());
            }
            if (patchBookingRequest.getToAddress() != null) {
                existingBookingRequest.setToAddress(patchBookingRequest.getToAddress());
            }
            if (patchBookingRequest.getNumberOfPeople() != 0) {
                existingBookingRequest.setNumberOfPeople(patchBookingRequest.getNumberOfPeople());
            }
            if (patchBookingRequest.getDistanceInKm() != 0) {
                existingBookingRequest.setDistanceInKm(patchBookingRequest.getDistanceInKm());
            }
            if (patchBookingRequest.getKmPrice() != 0) {
                existingBookingRequest.setKmPrice(patchBookingRequest.getKmPrice());
            }
            if (patchBookingRequest.getTripPrice() != 0) {
                existingBookingRequest.setTripPrice(patchBookingRequest.getTripPrice());
            }
            if (patchBookingRequest.getCarType() != null) {
                existingBookingRequest.setCarType(patchBookingRequest.getCarType());
            }
            if (patchBookingRequest.getBookingStatus() != null) {
                existingBookingRequest.setBookingStatus(patchBookingRequest.getBookingStatus());
            }

            bookingRequestRepository.save(existingBookingRequest);

            return transferToBookingRequestDto(existingBookingRequest);
        } else {
            throw new RecordNotFoundException("Booking request not found");
        }
    }

    public void deleteBooking(long id) {
        bookingRequestRepository.deleteById(id);
    }

    public List<BookingRequest> getBookingRequestsByStatus(BookingStatus status) {
        return bookingRequestRepository.findByBookingStatus(status);
    }

    // Is van client naar server
    private BookingRequest transferToBookingRequest(BookingRequestDto dto) {
        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setTripDate(dto.getTripDate());
        bookingRequest.setTripTime(dto.getTripTime());
        bookingRequest.setFromAddress(dto.getFromAddress());
        bookingRequest.setToAddress(dto.getToAddress());
        bookingRequest.setNumberOfPeople(dto.getNumberOfPeople());
        bookingRequest.setDistanceInKm(dto.getDistanceInKm());
        bookingRequest.setKmPrice(dto.getKmPrice());
        bookingRequest.setTripPrice(dto.getTripPrice());
        bookingRequest.setCarType(dto.getCarType());
        bookingRequest.setBookingStatus(dto.getBookingStatus());

        return bookingRequest;
    }

    public BookingRequestDto transferToBookingRequestDto(BookingRequest bookingRequest) {
        var dto = new BookingRequestDto();

        dto.bookingId = bookingRequest.getBookingId();
        if (bookingRequest.getCustomer() != null) {
            dto.customer = customerService.transferToCustomerDto(bookingRequest.getCustomer());
        }
        if (bookingRequest.getPlanner() != null) {
            dto.planner = plannerService.transferToPlannerDto(bookingRequest.getPlanner());
        }
        dto.tripDate = bookingRequest.getTripDate();
        dto.tripTime = bookingRequest.getTripTime();
        dto.fromAddress = bookingRequest.getFromAddress();
        dto.toAddress = bookingRequest.getToAddress();
        dto.numberOfPeople = bookingRequest.getNumberOfPeople();
        dto.distanceInKm = bookingRequest.getDistanceInKm();
        dto.kmPrice = bookingRequest.getKmPrice();
        dto.tripPrice = bookingRequest.getTripPrice();
        dto.carType = bookingRequest.getCarType();
        dto.bookingStatus = bookingRequest.getBookingStatus();

        return dto;
    }

    public double getCorrectKmPrice(CarType carType) {

        return carType.getPrice();
    }

    public double calculateTripPrice(CarType carType, double distanceInKM) {

        double basePrice = carType.getPrice();

        return Precision.round((basePrice * distanceInKM), 2);
    }
}
