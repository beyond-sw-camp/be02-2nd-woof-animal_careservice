package com.woof.api.cart.service;


import com.woof.api.cart.model.Cart;
import com.woof.api.cart.model.dto.CartDto;
import com.woof.api.cart.repository.CartRepository;
import com.woof.api.common.Response;
import com.woof.api.member.model.Member;
import com.woof.api.member.repository.MemberRepository;
import com.woof.api.productCeo.model.ProductCeo;
import com.woof.api.productManager.model.ProductManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    // 즐겨찾기 추가
    public void create(Member member, Cart cart, ProductManager productManager) {

        // CartRepository를 사용하여 새로운 Cart 엔티티를 데이터베이스에 저장
        cartRepository.save(Cart.builder()
                .member(member)
                .productManager(ProductManager.builder().idx(cart.getIdx()).build())
                .productCeo(ProductCeo.builder().idx(cart.getIdx()).build())
                .build());
    }

    // 즐겨찾기 목록 조회
    public Response list(String email) {

        Optional<Member> member = memberRepository.findByEmail(email);


        if (member.isPresent()) {
            List<Cart> carts = cartRepository.findAllByMember(Member.builder().id(member.get().getId()).build());

            List<CartDto> cartList = new ArrayList<>();
            for (Cart cart : carts) {
                ProductCeo productCeo = cart.getProductCeo();
                cartList.add(CartDto.builder()
                        .idx(cart.getIdx())
                        .productCeoName(cart.getProductCeo().getStoreName())// 업체명
                        .filename(cart.getProductCeo().getProductCeoImages().get(0).getFilename())// 업체 사진
                        .productManagerName(cart.getProductManager().getManagerName())// 매니저 이름
                        .build());

            }
            return Response.success("조회 성공");
        } else {

            return Response.error("0000");
        }
    }
//        return CartListRes.builder()
//                .code(1000)
//                .message("요청 성공.")
//                .success(true)
//                .isSuccess(true)
//                .result(cartDtos)
//                .build();


        // @Transactional
        public Response remove (Long idx, Member member) {
            // CartRepository를 사용하여 id 및 관련 멤버로 즐겨찾기 삭제
            cartRepository.deleteByIdAndMember(idx ,member);

            return Response.success("삭제 성공");
        }

    }

