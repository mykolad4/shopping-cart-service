package com.demchenko.microservices.shoppingcartservice.repository;

import com.demchenko.microservices.shoppingcartservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<Cart, String> {
}
