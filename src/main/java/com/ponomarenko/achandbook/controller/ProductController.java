package com.ponomarenko.achandbook.controller;

import com.ponomarenko.achandbook.model.Error;
import com.ponomarenko.achandbook.model.*;
import com.ponomarenko.achandbook.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final BrandService brandService;

    private final ProductService productService;

    private final ErrorService errorService;

    private final SizeService sizeService;

    private final ConnectService connectService;


    public ProductController(
            BrandService brandService,
            ProductService productService,
            ErrorService errorService,
            SizeService sizeService,
            ConnectService connectService) {
        this.brandService =  brandService;
        this.productService = productService;
        this.errorService = errorService;
        this.sizeService = sizeService;
        this.connectService = connectService;
    }

    @GetMapping
    public String productListAndAddPage (
            @RequestParam(name = "brandId", required = false) Brand brand,
            Model model) {
        if (brand == null) {
            brand = (Brand) model.getAttribute("brand");
        }

        List<Product> products = productService.findByBrandId(brand.getId());

        if (!products.isEmpty()){
            model.addAttribute("products",products);
        }
        model.addAttribute("brand", brand);
        return "admin/product-list";
    }

    @PostMapping
    public String productListAndAdd(
            @RequestParam("brandId") Brand brand,
            @Valid Product product,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            product.setBrand(brand);
            productService.saveProduct(product);
        }


        List<Product> products = productService.findByBrandId(brand.getId());
        model.addAttribute("brand", brand);
        model.addAttribute("products",products);
        return "admin/product-list";
    }

    @GetMapping("/update/{id}")
    public String productUpdateForm(@PathVariable("id") Product product, Model model){
        model.addAttribute("product",product);
        List<Error> errors = errorService.findAll();
        List<Size> sizes = sizeService.findAll();
        List<Connect> connects = connectService.findAll();
        model.addAttribute("connects",connects);
        model.addAttribute("errors",errors);
        model.addAttribute("sizes",sizes);
        model.addAttribute("types", Type.values());
        return "admin/product-update";
    }

    @PostMapping("/update")
    public String productUpdate(
            @RequestParam String name,
            @RequestParam("type") Type type,
            @RequestParam("id") Product product,
            @RequestParam Map<String,Object> form){
        try {
            Long connectId = Long.parseLong((String) form.get("connect"));
            Connect connect = connectService.findById(connectId);
            product.setConnect(connect);
        } catch (Exception ignored){
            product.setConnect(null);
        }

        try {
            Long errorId = Long.parseLong((String) form.get("error"));
            Error error = errorService.findById(errorId);
            product.setError(error);
        } catch (Exception ignored){
            product.setError(null);
        }

        try {
            Long sizeId = Long.parseLong((String) form.get("size"));
            Size size = sizeService.findById(sizeId);
            product.setSize(size);
        } catch (Exception ignored){
            product.setSize(null);
        }
        product.setName(name);
        product.setType(type);

        productService.saveProduct(product);
        return "redirect:/products?brandId=" + product.getBrand().getId();
    }

    @GetMapping("/delete/{id}")
    public String productDelete(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        Long brandId = product.getBrand().getId();
        productService.deleteById(id);
        return "redirect:/products?brandId="+ brandId;

    }

    @GetMapping("/addPackage")
    public String productsAddFromPackageForm(Model model){
        model.addAttribute("brands", brandService.findAll());
        return "admin/product-package-add";
    }

    @PostMapping("/addPackage")
    public String productsAddFromPackage(
            @RequestParam("brandId") Brand brand,
            @RequestParam("type") Type type,
            @RequestParam("file") MultipartFile file) throws IOException {
        productService.addProductsFromFile(brand, type, file);
        return "redirect:/products?brandId="+ brand.getId();
    }
}
