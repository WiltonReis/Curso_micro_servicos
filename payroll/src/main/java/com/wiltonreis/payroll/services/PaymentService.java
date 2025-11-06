package com.wiltonreis.payroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiltonreis.payroll.entities.Payment;
import com.wiltonreis.payroll.entities.Worker;
import com.wiltonreis.payroll.feignClient.WorkerFeignClient;

@Service
public class PaymentService {

    @Autowired
    private WorkerFeignClient workerFeignClient;
    
    public Payment getPayment(Long workerId, int days){

        Worker worker = workerFeignClient.findById(workerId).getBody();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
