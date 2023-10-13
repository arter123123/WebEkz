package ru.ulstu.is.sbapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.DTO.OrderDto;
import ru.ulstu.is.sbapp.DTO.ProductDto;
import ru.ulstu.is.sbapp.service.OrderService;
import ru.ulstu.is.sbapp.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final OrderService orderService;
    public ProductController(ProductService productService, OrderService orderService){
        this.productService = productService;
        this.orderService = orderService;
    }
    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products",
                productService.findAllProducts().stream()
                        .map(ProductDto::new)
                        .toList());
        return "product";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editProduct(@PathVariable(required = false) Long id,
                                          Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("productDto", new ProductDto());
            model.addAttribute("orders",
                    orderService.findAllOrders().stream()
                            .map(OrderDto::new)
                            .toList());
        } else {
            model.addAttribute("productId", id);
            model.addAttribute("orders",
                    orderService.findAllOrders().stream()
                            .map(OrderDto::new)
                            .toList());
            model.addAttribute("productDto", new ProductDto(productService.findProduct(id)));
        }
        return "product-edit";
    }
    @PostMapping(value = {"/edit", "edit/{id}"})
    public String saveProduct(@PathVariable(required = false) Long id,
                                          @RequestParam(value = "orderId") Long order_id,
                                          @ModelAttribute @Valid ProductDto ProductDto,
                                          BindingResult bindingResult,
                                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "product-edit";
        }

        ProductDto.setOrder(orderService.findOrder(order_id));
        if (id == null || id <= 0) {
            productService.createProduct(ProductDto.getOrder().getId(), ProductDto.getProductName());
        } else {
            productService.updateProduct(id, ProductDto.getOrder().getId(), ProductDto.getProductName());
        }
        return "redirect:/product";
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/product";
    }
    
}
