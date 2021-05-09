package com.demchenko.microservices.shoppingcartservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String title;
    private String desc;
    private String imagePath;
    private double unitPrice;
}
