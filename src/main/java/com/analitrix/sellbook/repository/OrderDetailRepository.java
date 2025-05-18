package com.analitrix.sellbook.repository;

import com.analitrix.sellbook.model.core.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

}
