package com.demchenko.microservices.shoppingcartservice.service;

import com.demchenko.microservices.shoppingcartservice.model.Cart;
import com.demchenko.microservices.shoppingcartservice.model.CartItem;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoShoppingCartService implements ShoppingCartService {

    private final MongoTemplate mongoTemplate;
    @Override
    public Cart addItem(String cartId, CartItem item) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(cartId));
        Update update = new Update();
        update.addToSet("items", item);
        UpdateResult result = mongoTemplate.upsert(query, update, Cart.class);
        return this.getCart(cartId == null ? result.getUpsertedId().toString() : cartId);
    }

    @Override
    public Cart getCart(String cartId) {
        return mongoTemplate.findById(cartId, Cart.class);
    }
}
