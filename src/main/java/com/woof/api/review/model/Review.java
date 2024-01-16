package com.woof.api.review.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String username;
    private String text;
    private Integer productNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_id")
     Product product;

    @Builder
    public Review(Long idx, String username, String text, Product product, Integer productNumber){
        this.idx = idx;
        this.username = username;
        this.text = text;
        this.product = product;
        this.productNumber = productNumber;
    }
}