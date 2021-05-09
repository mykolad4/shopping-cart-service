package com.demchenko.microservices.shoppingcartservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartItem {
    private String productId;
    private int quantity;
    private double totalItemPrice;
}
