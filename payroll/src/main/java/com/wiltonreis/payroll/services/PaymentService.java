package com.wiltonreis.payroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wiltonreis.payroll.entities.Payment;
import com.wiltonreis.payroll.entities.Worker;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;
    
    public Payment getPayment(Long workerId, int days){
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", workerId.toString());

        Worker worker = new RestTemplate().getForObject(workerHost + "/workers/{id}", Worker.class, uriVariables);

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
