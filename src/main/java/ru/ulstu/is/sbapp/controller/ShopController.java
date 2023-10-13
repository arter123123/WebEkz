package ru.ulstu.is.sbapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.DTO.ShopDto;
import ru.ulstu.is.sbapp.service.ShopService;

import javax.validation.Valid;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    public ShopController(ShopService shopService){
        this.shopService=shopService;
    }
    @GetMapping
    public String getShops(Model model) {
        model.addAttribute("shops",
                shopService.findAllShops().stream()
                        .map(ShopDto::new)
                        .toList());
        return "shop";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editShop(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("shopDto", new ShopDto());
        } else {
            model.addAttribute("shopId", id);
            model.addAttribute("shopDto", new ShopDto(shopService.findShop(id)));
        }
        return "shop-edit";
    }

    @PostMapping(value = {"/edit", "edit/{id}"})
    public String saveShop(@PathVariable(required = false) Long id,
                               @ModelAttribute @Valid ShopDto shopDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "shop-edit";
        }
        if (id == null || id <= 0) {
            shopService.createShop(shopDto.getShopName());
        } else {
            shopService.updateShop(id, shopDto.getShopName());
        }
        return "redirect:/shop";
    }

    @PostMapping("/delete/{id}")
    public String deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
        return "redirect:/shop";
    }

}
