package com.novi.cabforyou.services;

import com.novi.cabforyou.dtos.BookingDto;
import com.novi.cabforyou.dtos.InvoiceDto;
import com.novi.cabforyou.exceptions.RecordNotFoundException;
import com.novi.cabforyou.models.Booking;
import com.novi.cabforyou.models.BookingStatus;
import com.novi.cabforyou.models.Invoice;
import com.novi.cabforyou.repositories.BookingRepository;
import com.novi.cabforyou.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final BookingRepository bookingRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, BookingRepository bookingRepository) {
        this.invoiceRepository = invoiceRepository;
        this.bookingRepository = bookingRepository;
    }

    //Is OK
    public List<InvoiceDto> getAllInvoices() {
        List<InvoiceDto> invoiceDto = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice i: invoices){
            invoiceDto.add(transferToDto(i));
        }
        return invoiceDto;
    }

    //Is OK
    public InvoiceDto getInvoice(long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if(invoice.isPresent()) {
            return transferToDto(invoice.get());
        } else {
            throw new RecordNotFoundException("No invoice found!!!");
        }
    }
//  ==========================================================
    public InvoiceDto createInvoice(InvoiceDto invoiceDto, BookingDto bookingDto) {

        Invoice invoice = transferToInvoice(invoiceDto);
        Optional<Booking> tripBooking = bookingRepository.findById(bookingDto.getBookingId());

        if (tripBooking.isPresent() && tripBooking.get().getBookingStatus() == BookingStatus.COMPLETED) {
        invoiceRepository.save(invoice);
        } else {
        throw new RecordNotFoundException("No invoice found!!!");
        }
        return transferToDto(invoice);
}

//  ===========================================================

    public void updateInvoice(long id, InvoiceDto newInvoice) {
        if(!invoiceRepository.existsById(id)) throw new RecordNotFoundException();

        Invoice invoice = invoiceRepository.findById(id).get();

        invoice.setInvoiceId(newInvoice.getInvoiceId());
        invoice.setInvoiceNumber(newInvoice.getInvoiceNumber());
        invoice.setBookingNumber(newInvoice.getBookingNumber());
        invoice.setTotalAmount(newInvoice.getTotalAmount());
        invoice.setCreatedAt(newInvoice.getCreatedAt());

        invoiceRepository.save(invoice);
    }



    public void deleteInvoice(long id) {
        invoiceRepository.deleteById(id);
    }

    private InvoiceDto transferToDto(Invoice invoice) {
        var dto = new InvoiceDto();

        dto.invoiceId = invoice.getInvoiceId();
        dto.invoiceNumber = invoice.getInvoiceNumber();
        dto.bookingNumber = invoice.getBookingNumber();
        dto.totalAmount = invoice.getTotalAmount();
        dto.createdAt = invoice.getCreatedAt();

        return dto;
    }

    private Invoice transferToInvoice(InvoiceDto dto) {
        Invoice invoice = new Invoice();

        invoice.setInvoiceId(dto.getInvoiceId());
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setBookingNumber(dto.getBookingNumber());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setCreatedAt(dto.getCreatedAt());

        return invoice;
    }

}
