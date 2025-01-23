package com.analitrix.sellbook.repositories;

import com.analitrix.sellbook.models.InvoiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceUserRepository extends JpaRepository <InvoiceUser, String> {

}
