package com.ponomarenko.achandbook.controller;

import com.ponomarenko.achandbook.model.Brand;
import com.ponomarenko.achandbook.service.BrandService;
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
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    @Value("${upload.path}")
    private String uploadPath;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String adminMainPage(Model model){
        List<Brand> brands = brandService.findAll();
        model.addAttribute("brands", brands);
        return "admin/index";
    }

    @GetMapping("/create")
    public String brandCreateForm(Brand brand){
        return "admin/brand-create";
    }

    @PostMapping("/create")
    public String createBrand(
            Brand brand,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        uploadFile(brand, file);

        brandService.saveBrand(brand);
        return "redirect:/brands";
    }


    @GetMapping("/delete/{id}")
    public String deleteBrand(@PathVariable("id") Long id){
        brandService.deleteById(id);
        return "redirect:/brands";
    }

    @GetMapping("/update/{id}")
    public String updateBrandForm(
            @PathVariable("id") Brand brand,
            Model model){
        model.addAttribute("brand",brand);
        return "admin/brand-update";
    }

    @PostMapping("/update")
    public String updateBrand(
            @RequestParam String name,
            @RequestParam("id") Brand brand,
            @RequestParam("file") MultipartFile file) throws IOException {

        uploadFile(brand, file);

        brand.setName(name);
        brandService.saveBrand(brand);
        return "redirect:/brands";
    }

    private void uploadFile(Brand brand, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()){
                uploadFolder.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            brand.setFilename(resultFileName);
        }
    }
}
