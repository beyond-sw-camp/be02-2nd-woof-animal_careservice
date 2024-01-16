package com.woof.api.cart.controller;




import com.woof.api.cart.model.dto.CartCreateReq;
import com.woof.api.cart.service.CartService;
import com.woof.api.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;

    @RequestMapping(method = RequestMethod.POST, value = "/in/{idx}")
    public ResponseEntity cartIn(@AuthenticationPrincipal Member member, @PathVariable Long idx) {
                             // Member의 UserDetails 객체에 접근가능
        cartService.create(member, cart);
        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list(@AuthenticationPrincipal Member member) {

        return ResponseEntity.ok().body(cartService.list(member.getEmail()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cancel/{idx}")
    public ResponseEntity remove(@AuthenticationPrincipal Member member,@PathVariable Long idx) {
        cartService.remove(idx, member);
        return ResponseEntity.ok().body("ok");
    }
}
