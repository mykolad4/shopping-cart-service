package com.demchenko.microservices.shoppingcartservice.controller;

import com.demchenko.microservices.shoppingcartservice.model.Cart;
import com.demchenko.microservices.shoppingcartservice.model.CartItem;
import com.demchenko.microservices.shoppingcartservice.model.Product;
import com.demchenko.microservices.shoppingcartservice.service.ShoppingCartService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {

    private final RestTemplate restTemplate;
    private final ShoppingCartService shoppingCartService;
    private final EurekaClient discoveryClient;

    @PostMapping("/cart/{cartId}/item")
    public Cart addItem(@PathVariable String cartId, @RequestBody CartItem item) {
        if (cartId != null && item != null && item.getProductId() != null) {
            //get instance home url
            InstanceInfo instance = discoveryClient.getNextServerFromEureka("product_catalog",false);

            if (instance!=null){
                String productCatalogUrl = instance.getHomePageUrl()+"/product/"+ item.getProductId();
                // get product details
                Product itemProduct = restTemplate.getForObject(productCatalogUrl,Product.class);

                if (itemProduct != null && itemProduct.getId() != null) {
                    // adding total item price in the shopping cart item
                    item.setTotalItemPrice(itemProduct.getUnitPrice() * item.getQuantity());
                    return shoppingCartService.addItem(cartId, item);
                }
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "item product not found");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cart or item missing");
    }

}
