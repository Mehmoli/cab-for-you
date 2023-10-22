package com.novi.cabforyou.dtos;

import com.novi.cabforyou.models.Customer;

import java.time.LocalDate;

public class FeedbackDto {

    public Long feedbackId;

    public String feedback;

    public Double rating;

    public LocalDate submitDate;

    public Customer feedbackOfCustomer;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public Customer getFeedbackOfCustomer() {
        return feedbackOfCustomer;
    }

    public void setFeedbackOfCustomer(Customer feedbackOfCustomer) {
        this.feedbackOfCustomer = feedbackOfCustomer;
    }
}
