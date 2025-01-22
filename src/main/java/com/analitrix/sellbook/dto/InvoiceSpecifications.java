package com.analitrix.sellbook.dto;

import com.analitrix.sellbook.model.Invoice;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InvoiceSpecifications {
    public static Specification<Invoice> filterBy(String invoiceUser) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (invoiceUser != null && !invoiceUser.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("invoiceUser").as(String.class)), "%" + invoiceUser.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
