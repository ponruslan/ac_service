package com.ponomarenko.achandbook.controller;

import com.ponomarenko.achandbook.model.Connect;
import com.ponomarenko.achandbook.model.Product;
import com.ponomarenko.achandbook.service.ConnectService;
import com.ponomarenko.achandbook.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/connections")
public class ConnectController {

    private final ConnectService connectService;

    private final ProductService productService;

    public ConnectController(ConnectService connectService, ProductService productService) {
        this.connectService = connectService;
        this.productService = productService;
    }

    @GetMapping("/create/{id}")
    public String createConnectForm(
            @PathVariable("id") Long id,
            @RequestParam("productId") Long productId,
            Model model){
        List<Product> products = productService.findByBrandId(id);
        model.addAttribute("products",products);
        model.addAttribute("brandId", id);
        model.addAttribute("productId", productId);
        return "admin/connect-create";
    }

    @PostMapping("/create")
    public String createConnect(
            @RequestParam("brandId") Long id,
            Connect connect){

        connectService.saveConnect(connect);
        return "redirect:/products?brandId=" + id;
    }

    @GetMapping("/update/{id}")
    public String updateErrorForm(
            @PathVariable("id") Connect connect,
            @RequestParam(value = "brandId", required = false) Long brandId,
            Model model){
        List<Product> products;
        if (brandId != null) {
            products = productService.findByBrandId(brandId);
            model.addAttribute("brandId", brandId);
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products",products);
        model.addAttribute("connect",connect);

        return "/admin/connect-update";
    }

    @PostMapping("/update")
    public String updateError(
            @RequestParam(value = "brandId", required = false) Long brandId,
            Connect connect) {
        connectService.saveConnect(connect);
        if (brandId == null) return "redirect:/connections/all";
        return "redirect:/products?brandId="+brandId;
    }

    @GetMapping("/delete/{id}")
    public String connectDelete(@PathVariable("id") Long id) {
        connectService.deleteById(id);
        return "redirect:/connections/all";

    }


    @GetMapping("/all")
    public String connectAll(Model model){
        List<Connect> connections = connectService.findAll();
        if (!connections.isEmpty()){
            model.addAttribute("connections", connections);
        }
        return "admin/connect-list";
    }

}
