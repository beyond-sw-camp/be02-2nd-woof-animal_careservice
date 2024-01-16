package com.woof.api.payment.scheduler;


import com.siot.IamportRestClient.exception.IamportResponseException;
import com.woof.api.payment.service.PaymentService;
import com.woof.api.payment.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SubscribeScheduler {
    private final SubscribeService subscribeService;
    @Scheduled(cron = "* * 11 * * *") // 1초에 1000
    public void test () throws IamportResponseException, IOException {
        System.out.println("test");
//        subscribeService.regularPayment();
    }
}
