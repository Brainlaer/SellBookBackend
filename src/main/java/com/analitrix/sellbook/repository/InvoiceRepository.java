package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.Book;
import com.analitrix.sellbook.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InvoiceRepository extends JpaRepository<Invoice, String>, JpaSpecificationExecutor<Invoice> {
    Page<Invoice> findAll(Pageable pageable);


}
