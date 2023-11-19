package com.novi.cabforyou.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue
    private Long feedbackId;


    private String feedback;


    private Double rating;


    private LocalDate submitDate;

    @ManyToOne
    @JoinColumn(name = "customer_username")
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
