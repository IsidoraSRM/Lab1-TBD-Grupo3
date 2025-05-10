package com.Docdelivery.Backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Docdelivery.Backend.Entity.OrderSummaryEntity;
import com.Docdelivery.Backend.Repository.OrderSummaryRepository;

@Service
public class OrderSummaryService {

    @Autowired
    private OrderSummaryRepository OrderSummaryrepository;



    public List<OrderSummaryEntity> getOrderSummaries() {
        return OrderSummaryrepository.findAll();
    }
}
