package com.example.lombard.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (CartItem item : items) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem item : items) {
            if (item.getProduct().getPrice() > 12){
                totalPrice += item.getProduct().getPrice() * 1.5 * item.getQuantity();
            }
            else if (item.getProduct().getPrice() <= 12){
                totalPrice += item.getProduct().getPrice() * 1.2 * item.getQuantity();
            }
        }
        return totalPrice;
    }
}

