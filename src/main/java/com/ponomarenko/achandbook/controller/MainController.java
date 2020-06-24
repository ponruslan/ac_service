package com.ponomarenko.achandbook.controller;

import com.ponomarenko.achandbook.model.Brand;
import com.ponomarenko.achandbook.model.Product;
import com.ponomarenko.achandbook.model.Type;
import com.ponomarenko.achandbook.service.BrandService;
import com.ponomarenko.achandbook.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String main(Model model){
        List<Brand> brands = brandService.findAll();
        model.addAttribute("brands", brands);
        return "main";
    }

    @GetMapping("/type")
    public String typeSelect(
            @RequestParam String brandId,
            Model model){
        model.addAttribute("brandId", brandId);
        return "type";
    }

    @GetMapping("/product")
    public String productSelectForm(
            @RequestParam("brandId") Brand brand,
            @RequestParam("type") Type type,
            Model model){
        List<Product> products = productService.findByBrandAndType(brand,type);
        model.addAttribute("products",products);
        model.addAttribute("brand", brand);
        model.addAttribute("type", type.toString());
        return "product";
    }

    @GetMapping("/product/{id}")
    public String productPage(
            @PathVariable("id") Product currentProduct,
            Model model) {
        model.addAttribute("brand", currentProduct.getBrand());
        List<Product> products = productService.findByBrandAndType(currentProduct.getBrand(),currentProduct.getType());
        model.addAttribute("products",products);
        model.addAttribute("currentProduct", currentProduct);
        model.addAttribute("type", currentProduct.getType().toString());
        return "product";
    }

}
