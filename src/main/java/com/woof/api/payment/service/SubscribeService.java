package com.woof.api.payment.service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.AgainPaymentData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.woof.api.member.model.Member;
import com.woof.api.payment.model.Subscribe;
import com.woof.api.payment.model.SubscribeInfo;
import com.woof.api.payment.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SubscribeService {
    private final IamportClient iamportClient;
    private final SubscribeRepository subscribeRepository;

    public void regularPayment() throws IamportResponseException, IOException {
        AgainPaymentData again_data = new AgainPaymentData("test14", new Date().toString(), BigDecimal.valueOf(1005));
        IamportResponse<Payment> payment_response = iamportClient.againPayment(again_data);
        System.out.println(payment_response.getResponse());
    }

    public Boolean subscribeValidation(Member member, String impUid) throws IamportResponseException, IOException {
        IamportResponse<Payment> response = iamportClient.paymentByImpUid(impUid);

        Integer amount = response.getResponse().getAmount().intValue();

        String subscribeType = StringUtils.substring(response.getResponse().getMerchantUid(), 0, -19);
        Integer price = SubscribeInfo.valueOf(subscribeType).getPrice();
        // 구독한 사람이 가입한 타입에 맞는 가격으로 결제 되었는지 확인
        if (amount.equals(SubscribeInfo.valueOf(subscribeType).getPrice())) {
            subscribeRepository.save(
                    Subscribe.builder()
                            .member(member)
                            .subscribeInfo(SubscribeInfo.valueOf(subscribeType))
                    .build()
            );
            System.out.println("정상");
            return true;
        }

        System.out.println("비정상");

        return false;
    }
}
