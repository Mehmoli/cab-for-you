package com.novi.cabforyou.models;

import jakarta.persistence.*;


@Entity
@Table(name = "invoices")
public class Invoice extends CreatedAtDateAndTime {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "booking_number")
    private String bookingNumber;

    @Column(name = "total")
    private Long totalAmount;

    @OneToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

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

    @Override
    public String toString() {
        return String.format("Invoice[invoiceId=%d, bookingNumber='%s', totalAmount='%s']",
                invoiceId, bookingNumber, totalAmount);
    }
}
