package com.demchenko.microservices.shoppingcartservice.service;

import com.demchenko.microservices.shoppingcartservice.model.Cart;
import com.demchenko.microservices.shoppingcartservice.model.CartItem;

public interface ShoppingCartService {
    Cart addItem(String cartId, CartItem item);
    Cart getCart(String cartId);
}
