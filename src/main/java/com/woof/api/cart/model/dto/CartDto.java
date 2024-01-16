package com.woof.api.cart.model.dto;

import com.woof.api.member.model.Member;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@Builder
public class CartDto {

    Long idx;

    Long productCeoIdx;

    Integer brandIdx;
    @NotNull
    @Pattern(regexp = "[가-힣0-9]{2,5}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$")
    String name;

    String filename;

    Long memberId;
}
