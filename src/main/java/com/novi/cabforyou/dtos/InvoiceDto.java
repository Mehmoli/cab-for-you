package com.novi.cabforyou.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonIgnoreProperties(
        value = {"id", "created_at"},
        allowGetters = true
)
public class InvoiceDto {
    @JsonProperty("id")
    @JsonIgnore
    public Long invoiceId;
    @JsonProperty("invoice_number")
    @NotNull
    public String invoiceNumber;
    @JsonProperty("booking_number")
    @NotNull
    public String bookingNumber;
    @JsonProperty("total")
    @NotNull
    public Long totalAmount;
    @JsonProperty("created_at")
    @JsonIgnore
    public LocalDateTime createdAt = LocalDateTime.now();

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}

