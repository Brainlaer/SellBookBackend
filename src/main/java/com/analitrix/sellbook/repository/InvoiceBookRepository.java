package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.model.core.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceBookRepository extends JpaRepository<OrderDetail, String>{


}
