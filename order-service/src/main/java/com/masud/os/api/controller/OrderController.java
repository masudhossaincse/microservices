package com.masud.os.api.controller;

import com.masud.os.api.common.Payment;
import com.masud.os.api.common.TransactionRequest;
import com.masud.os.api.common.TransactionResponse;
import com.masud.os.api.entity.Order;
import com.masud.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest)
    {

        return orderService.saveOrder(transactionRequest);
    }
}
