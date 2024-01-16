package com.woof.api.cart.model.dto;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class CartCreateReq {

    @NotNull
    Long productCeoIdx;
}
