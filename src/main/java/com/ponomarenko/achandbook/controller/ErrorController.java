package com.ponomarenko.achandbook.controller;

import com.ponomarenko.achandbook.model.Error;
import com.ponomarenko.achandbook.model.Product;
import com.ponomarenko.achandbook.service.ErrorService;
import com.ponomarenko.achandbook.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/errors")
public class ErrorController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ErrorService errorService;

    @GetMapping("/create/{id}")
    public String ErTest(
            @PathVariable("id") Long id,
            @RequestParam("productId") Long productId,
            Model model){
        List<Product> products = productService.findByBrandId(id);
        model.addAttribute("products", products);
        model.addAttribute("brandId", id);
        model.addAttribute("productId", productId);
        return "admin/error-create";
    }

    @PostMapping("/create")
    public String createError(
            @RequestParam("brandId") Long id,
            Error error){
        errorService.saveError(error);
        return "redirect:/products?brandId="+id;
    }

    @GetMapping("/update/{id}")
    public String updateErrorForm(
            @PathVariable("id") Error error,
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
        model.addAttribute("error",error);
        return "/admin/error-update";
    }

    @PostMapping("/update")
    public String updateError(
            @RequestParam(value = "brandId", required = false) Long brandId,
            Error error) {
        errorService.saveError(error);
        if (brandId == null) return "redirect:/errors/all";
        return "redirect:/products?brandId="+brandId;
    }

    @GetMapping("/delete/{id}")
    public String errorDelete(@PathVariable("id") Long id) {
        errorService.deleteById(id);
        return "redirect:/errors/all";

    }

    @GetMapping("/all")
    public String errorsAll(Model model){
        List<Error> errors = errorService.findAll();
        if (!errors.isEmpty()){
            model.addAttribute("errors", errors);
        }
        return "admin/error-list";
    }

}
