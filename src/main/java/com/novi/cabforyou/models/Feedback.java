package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue
    private Long feedbackId;

    @NotNull(message = "Add feedback here")
    private String feedback;

    @NotNull(message = "Please give rating")
    @Min(value = 1, message = "Min value is 1")
    @Max(value = 5, message = "Max value is 5")
    private Double rating;

    @NotNull(message = "Enter the feedback submission date")
	@PastOrPresent
	@JsonFormat(pattern = "dd/mm/yyyy")
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
