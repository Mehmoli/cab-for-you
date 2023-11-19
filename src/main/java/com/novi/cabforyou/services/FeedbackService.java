package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.FeedbackDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Customer;
import com.novi.cabforyou.models.Feedback;
import com.novi.cabforyou.repositories.CustomerRepository;
import com.novi.cabforyou.repositories.FeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final CustomerRepository customerRepository;

    private final CustomerService customerService;

    public FeedbackService(FeedbackRepository feedbackRepository,
                           CustomerRepository customerRepository,
                           CustomerService customerService) {
        this.feedbackRepository = feedbackRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    public List<FeedbackDto> getAllFeedbacks() {
        List<FeedbackDto> feedbackDtos = new ArrayList<>();
        List<Feedback> feedbacks = feedbackRepository.findAll();
        for (Feedback f : feedbacks) {
            feedbackDtos.add(transferToFeedbackDto(f));
        }
        return feedbackDtos;
    }

    public FeedbackDto getFeedback(Long id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isPresent()) {
            return transferToFeedbackDto(feedback.get());
        } else {
            throw new RecordNotFoundException();
        }
    }

    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = transferToFeedback(feedbackDto);
        feedbackRepository.save(feedback);

        feedbackDto = transferToFeedbackDto(feedback);
        return feedbackDto;
    }

    public void updateFeedback(long id, FeedbackDto newFeedback) {
        if (!feedbackRepository.existsById(id)) {
            throw new RecordNotFoundException();
        }

        Optional<Feedback> feedbackOpt = feedbackRepository.findById(id);
        if (feedbackOpt.isPresent()) {
            Feedback feedback = feedbackOpt.get();

            feedback.setFeedback(newFeedback.getFeedback());
            feedback.setRating(newFeedback.getRating());
            feedback.setSubmitDate(newFeedback.getSubmitDate());
            if (newFeedback.customer != null) {
                Optional<Customer> customerOpt = customerRepository.findByUsername(newFeedback.customer.username);
                if (customerOpt.isPresent()) {
                    Customer customer = customerOpt.get();
                    feedback.setCustomer(customer);
                }
            }

            feedbackRepository.save(feedback);
        }
    }

    @Transactional
    public void deleteFeedback(long id) {
        feedbackRepository.findById(id).ifPresent(feedback -> {
            Customer customer = feedback.getCustomer();
            customer.getFeedbacks().remove(feedback);
            feedbackRepository.delete(feedback);
        });
    }

    private Feedback transferToFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDto.getFeedbackId());
        feedback.setFeedback(feedbackDto.getFeedback());
        feedback.setRating(feedbackDto.getRating());
        feedback.setSubmitDate(feedbackDto.getSubmitDate());
        if (feedbackDto.customer != null) {
            Optional<Customer> customerOpt = customerRepository.findByUsername(feedbackDto.customer.username);
            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                feedback.setCustomer(customer);
            }
        }

        return feedback;
    }

    private FeedbackDto transferToFeedbackDto(Feedback feedback) {
        var dto = new FeedbackDto();
        dto.feedbackId = feedback.getFeedbackId();
        dto.feedback = feedback.getFeedback();
        dto.rating = feedback.getRating();
        dto.submitDate = feedback.getSubmitDate();
        dto.customer = customerService.transferToCustomerDto(feedback.getCustomer());

        return dto;
    }
}
