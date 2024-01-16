package com.woof.api.payment.controller;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.woof.api.common.Response;
import com.woof.api.member.model.Member;
import com.woof.api.payment.service.PaymentService;
import com.woof.api.payment.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/subscribe")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SubscribeController {
    private final SubscribeService subscribeService;
    @RequestMapping(method = RequestMethod.GET, value = "/validate")
    public Response validateSubscribe(@AuthenticationPrincipal Member member, String impUid) throws IamportResponseException, IOException {
        if(subscribeService.subscribeValidation(member, impUid)) {
            return Response.success("결제 성공");
        }

        return Response.error("결제 금액 이상");
    }


}
