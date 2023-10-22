package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.FeedbackDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Feedback;
import com.novi.cabforyou.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    private FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository){
        this.feedbackRepository =feedbackRepository;
    }


    public List<FeedbackDto> getAllFeedbacks() {

        List<FeedbackDto> feedbackDto = new ArrayList<>();
        List<Feedback> feedbacks = feedbackRepository.findAll();
        for (Feedback f: feedbacks){
            feedbackDto.add(transferToFeedbackDto(f));
        }
        return feedbackDto;
        
    }

    public FeedbackDto getFeedback(Long id) {
        
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if(!feedback.isPresent()){
            throw new RecordNotFoundException();
        } else {
            return transferToFeedbackDto(feedback.get());
        }
        
    }

    public FeedbackDto addFeedback(FeedbackDto feedbackDto) {
        
        Feedback feedback = transferToFeedback(feedbackDto);
        feedbackRepository.save(feedback);
        return feedbackDto;
        
    }

    public void updateFeedback(long id, FeedbackDto newFeedback) {

        if(!feedbackRepository.existsById(id)) throw new RecordNotFoundException();

        Feedback feedback = feedbackRepository.findById(id).get();

        feedback.setFeedback(newFeedback.getFeedback());
        feedback.setRating(newFeedback.getRating());
        feedback.setSubmitDate(newFeedback.getSubmitDate());
        feedback.setFeedbackOfCustomer(newFeedback.getFeedbackOfCustomer());

        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(long id) {
        feedbackRepository.deleteById(id);
    }

    private Feedback transferToFeedback(FeedbackDto feedbackDto){
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDto.feedbackId);
        feedback.setFeedback(feedbackDto.getFeedback());
        feedback.setRating(feedbackDto.getRating());
        feedback.setSubmitDate(feedbackDto.getSubmitDate());
        feedback.setFeedbackOfCustomer(feedbackDto.getFeedbackOfCustomer());


        return feedback;
    }

    private FeedbackDto transferToFeedbackDto(Feedback feedback) {

        var dto = new FeedbackDto();

        dto.feedbackId = feedback.getFeedbackId();
        dto.feedback = feedback.getFeedback();
        dto.rating = feedback.getRating();
        dto.submitDate = feedback.getSubmitDate();
        dto.feedbackOfCustomer =feedback.getFeedbackOfCustomer();

        return dto;

    }

}
