package com.novi.cabforyou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedbackId;

    @NotNull(message = "Add feedback here")
    private String feedback;

    @NotNull(message = "Please give rating")
//  @Pattern(regexp = "^((?=.*[*])(?=.*[1-5])){2}$",
//  message = "Rating must contain atleast 1 (*) and 1 digit from 1 to 5, length should be 2")
    private Double rating;

    @NotNull(message = "Enter the feedback submission date")
//	@PastOrPresent
//	@JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDate submitDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Customer feedbackOfCustomer;

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
