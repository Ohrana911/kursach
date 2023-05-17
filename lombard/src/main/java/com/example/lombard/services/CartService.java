package com.example.lombard.services;

import com.example.lombard.models.Cart;
import com.example.lombard.models.CartItem;
import com.example.lombard.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final Cart cart = new Cart();
    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    public void addToCart(Long productId, int quantity) {
        Product product = productService.getProductById(productId);
        CartItem cartItem = new CartItem(product, quantity);
        cart.addItem(cartItem);
    }

    public void removeFromCart(Long productId) {
        List<CartItem> items = cart.getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId().equals(productId)) {
                cart.removeItem(items.get(i));
                break;
            }
        }
    }

    public Cart getCart() {
        return cart;
    }
}
