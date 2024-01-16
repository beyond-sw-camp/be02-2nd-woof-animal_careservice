package com.woof.api.cart.service;


import com.woof.api.cart.model.Cart;
import com.woof.api.cart.model.dto.CartDto;
import com.woof.api.cart.repository.CartRepository;
import com.woof.api.common.Response;
import com.woof.api.member.model.Member;
import com.woof.api.productCeo.model.ProductCeo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    // 즐겨찾기 추가
    public void create(Member member, Cart cart) {

        // CartRepository를 사용하여 새로운 Cart 엔티티를 데이터베이스에 저장
        cartRepository.save(Cart.builder()
                .member(member)
                .productCeo(ProductCeo.builder()
                .idx(cart.getIdx())
                .build())
                .build());
    }

    // 즐겨찾기 목록 조회
    public Response list(Member member) {

        // 레포지토리에서 멤버의 카트 목록을 가져옴
        List<Cart> cartList = cartRepository.findAllByMember(member);

        // 검색된 Cart 엔티티를 CartDto로 변환하여 응답 형식을 구성
        List<CartDto> cartDtos = new ArrayList<>();
//        if (member != null) {
            for (Cart cart : cartList) {
                CartDto dto = CartDto.builder()
                        .idx(cart.getIdx())
                        .name(cart.getProductCeo().getStoreName())// 업체명
                        .memberId(member.getId())     // 사용자 id
                        .filename(cart.getProductCeo().getProductCeoImages().get(0).getFilename())// 업체 사진
                        .build();
                cartDtos.add(dto);
            }
            return Response.success("조회 성공");
        }

//        return CartListRes.builder()
//                .code(1000)
//                .message("요청 성공.")
//                .success(true)
//                .isSuccess(true)
//                .result(cartDtos)
//                .build();


        @Transactional
        public Response remove (Member member, Long idx) {
            // CartRepository를 사용하여 id 및 관련 멤버로 즐겨찾기 삭제
            cartRepository.deleteByIdAndMember(idx, member);

            return Response.success("삭제 성공");
        }

    }
