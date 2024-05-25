package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.entity.InvoicePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicePersonRepository extends JpaRepository <InvoicePerson, Long> {
}
