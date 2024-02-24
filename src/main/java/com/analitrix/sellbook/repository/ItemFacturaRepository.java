package com.analitrix.sellbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.analitrix.sellbook.entity.FacturaItem;

public interface ItemFacturaRepository extends JpaRepository<FacturaItem, Long>{

}
