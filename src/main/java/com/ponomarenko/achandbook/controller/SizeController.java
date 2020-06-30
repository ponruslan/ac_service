package com.ponomarenko.achandbook.controller;

import com.ponomarenko.achandbook.model.Product;
import com.ponomarenko.achandbook.model.Size;
import com.ponomarenko.achandbook.service.ProductService;
import com.ponomarenko.achandbook.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/sizes")
public class SizeController {

    private final ProductService productService;

    private final SizeService sizeService;

    @Value("${upload.path}")
    private String uploadPath;

    public SizeController(ProductService productService, SizeService sizeService) {
        this.productService = productService;
        this.sizeService = sizeService;
    }

    @GetMapping("/create/{id}")
    public String createSizeForm(
            @PathVariable("id") Long id,
            @RequestParam("productId") Long productId,
            Model model,
            Size size) {
        List<Product> products = productService.findByBrandId(id);
        model.addAttribute("products", products);
        model.addAttribute("brandId", id);
        model.addAttribute("productId", productId);
        return "admin/size-create";
    }

    @PostMapping("/create")
    public String createSize(
            @RequestParam("brandId") Long id,
            Size size,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (!file.isEmpty()) System.out.println("BINGO");
        uploadFile(size, file);

        sizeService.saveSize(size);
        return "redirect:/products?brandId=" + id;
    }

    @GetMapping("/update/{id}")
    public String updateSizeForm(
            @PathVariable("id") Size size,
            @RequestParam(value = "brandId", required = false) Long brandId,
            Model model) {
        List<Product> products;
        if (brandId != null) {
            products = productService.findByBrandId(brandId);
            model.addAttribute("brandId", brandId);
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products", products);
        model.addAttribute("size", size);

        return "admin/size-update";

    }

    @PostMapping("/update")
    public String updateSize(
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam("file") MultipartFile file,
            Size size) throws IOException {

        uploadFile(size, file);

        sizeService.saveSize(size);
        if (brandId == null) return "redirect:/sizes/all";
        return "redirect:/products?brandId=" + brandId;
    }

    @GetMapping("/delete/{id}")
    public String sizeDelete(@PathVariable("id") Long id) {
        sizeService.deleteById(id);
        return "redirect:/sizes/all";

    }

    @GetMapping("/all")
    public String sizesAll(Model model) {
        List<Size> sizes = sizeService.findAll();
        if (!sizes.isEmpty()) {
            model.addAttribute("sizes", sizes);
        }
        return "admin/size-list";
    }

    private void uploadFile(Size size, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));
            size.setFilename(resultFileName);
        }
    }
}
