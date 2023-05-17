package com.example.lombard.controllers;
import com.example.lombard.models.Cart;
import com.example.lombard.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

//    @GetMapping("/cart")
//    public String cart(Model model) {
//        // Создаем объект Cart и добавляем в модель
//        Cart cart = new Cart();
//        model.addAttribute("cart", cart);
//        return "cart";
//    }
}