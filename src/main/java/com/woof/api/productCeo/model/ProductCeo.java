package com.woof.api.productCeo.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class ProductCeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String storeName;
    private String productName;
    private Integer phoneNumber;
    private Integer price;
    private String contents;

//    @OneToMany(mappedBy = "productCeo")
//    private List<Orders> orders = new ArrayList<>();      합칠 때 활성화


    @OneToMany(mappedBy = "productCeo", fetch = FetchType.EAGER)
    private List<ProductCeoImage> productCeoImages = new ArrayList<>();

//    @OneToMany(mappedBy = "productCeo")
//    private List<Review> reviews = new ArrayList<>();     합칠 때 활성화

}
