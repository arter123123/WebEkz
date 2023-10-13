package ru.ulstu.is.sbapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.DTO.OrderDto;
import ru.ulstu.is.sbapp.DTO.OrderDto;
import ru.ulstu.is.sbapp.service.OrderService;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @GetMapping
    public String getOrders(Model model) {
        model.addAttribute("orders",
                orderService.findAllOrders().stream()
                        .map(OrderDto::new)
                        .toList());
        return "order";
    }
    @GetMapping(value = {"/productlistex/{id}"})
    public String ExitProduct(@PathVariable Long id, Model model) {
        model.addAttribute("orderId", id);
        model.addAttribute("orderDto", new OrderDto(orderService.findOrder(id)));
        OrderDto currentOrd = new OrderDto(orderService.findOrder(id));
        model.addAttribute("productLists", currentOrd.getProductList());
        return "order-productlistex";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editOrder(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("orderDto", new OrderDto());
        } else {
            model.addAttribute("orderId", id);
            model.addAttribute("orderDto", new OrderDto(orderService.findOrder(id)));
        }
        return "order-edit";
    }

    @PostMapping(value = {"/edit", "edit/{id}"})
    public String saveOrder(@PathVariable(required = false) Long id,
                           @ModelAttribute @Valid OrderDto orderDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "order-edit";
        }
        if (id == null || id <= 0) {
            orderService.createOrder(orderDto.getPrice());
        } else {
            orderService.updateOrder(id, orderDto.getPrice());
        }
        return "redirect:/order";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/order";
    }
}
