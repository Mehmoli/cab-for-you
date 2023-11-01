package com.novi.cabforyou.repositories;

import com.novi.cabforyou.models.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Page<Invoice> findByInvoiceNumberOrderByCreatedAtDesc(String invoiceNumber, Pageable pageable);
    Page<Invoice> findByBookingNumberOrderByCreatedAtDesc(String bookingNumber, Pageable pageable);
}
