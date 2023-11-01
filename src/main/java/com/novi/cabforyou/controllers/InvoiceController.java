package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.BookingDto;
import com.novi.cabforyou.dtos.InvoiceDto;
import com.novi.cabforyou.services.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cabforyou/invoices")
public class InvoiceController {


    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("")
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {

        List<InvoiceDto> dtos = invoiceService.getAllInvoices();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}") //Now OK
    public ResponseEntity<InvoiceDto> getInvoice(@PathVariable("id") long id) {
        InvoiceDto dto = invoiceService.getInvoice(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto dto, BookingDto bookingDto) {
        InvoiceDto dto1 = invoiceService.createInvoice(dto, bookingDto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InvoiceDto> updateInvoice(@PathVariable("id") long id, @RequestBody InvoiceDto dto) {

        invoiceService.updateInvoice(id, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable("id") long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }


}


