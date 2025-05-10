package com.Docdelivery.Backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Docdelivery.Backend.Entity.OrderSummaryEntity;
import com.Docdelivery.Backend.Service.OrderSummaryService;

@RestController
@RequestMapping("/api/order-summaries")
public class OrderSummaryController {

    @Autowired
    private OrderSummaryService orderSummaryService;

    @GetMapping("/all")
    @Secured({"ROLE_ADMIN"}) //solo el ADMIN puede acceder a este endpoint
    public List<OrderSummaryEntity> getAllSummaries() {
        return orderSummaryService.getOrderSummaries();
    }
    
}
