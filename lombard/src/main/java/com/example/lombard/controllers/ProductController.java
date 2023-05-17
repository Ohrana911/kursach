package com.example.lombard.controllers;

import com.example.lombard.models.Product;
import com.example.lombard.services.CartService;
import com.example.lombard.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        if (productService.getProductById(id).getPeriod() >= 12){
            model.addAttribute("new_price",productService.getProductById(id).getPrice() * 0.3);
        }
        else if(productService.getProductById(id).getPeriod() < 12){
            model.addAttribute("new_price",productService.getProductById(id).getPrice() * 0.9);
        }
        return "product-info";


    }

    @PostMapping("/product/create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @PostMapping("/product/addToCart/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam(name = "quantity", required = true) int quantity){
        Product product = productService.getProductById(id);
//        if (product == null) {
//            // handle error
//            return "redirect:/";
//        }
        cartService.addToCart(id, quantity);
        return "redirect:/";
    }
}