package com.demchenko.microservices.shoppingcartservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@Document(collection = "cart")
public class Cart {
    @Id
    private String id;
    private List<CartItem> items;
}
