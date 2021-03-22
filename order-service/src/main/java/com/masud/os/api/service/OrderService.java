package com.masud.os.api.service;


import com.masud.os.api.common.Payment;
import com.masud.os.api.common.TransactionRequest;
import com.masud.os.api.common.TransactionResponse;
import com.masud.os.api.entity.Order;
import com.masud.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest transactionRequest)
    {
        String response = "";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        //rest call
        Payment paymentResponse = restTemplate.postForObject("http://localhost:9191/payment/doPayment",payment,Payment.class);

        response = paymentResponse.getPaymentStatus().equals("success")?"payment successfully": "failed";


        orderRepository.save(order);
        return new TransactionResponse(order,paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
