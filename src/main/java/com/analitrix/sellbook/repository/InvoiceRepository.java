package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
