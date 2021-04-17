package com.example.projekt.controller;

import com.example.projekt.model.Brand;
import com.example.projekt.model.Category;
import com.example.projekt.model.Product;
import com.example.projekt.repository.BrandRepository;
import com.example.projekt.repository.ProductRepository;
import com.example.projekt.service.IBrandService;
import com.example.projekt.service.ICategoryService;
import com.example.projekt.service.IProductService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController
{
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/product_images";

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private BrandRepository brandRepository;


    @GetMapping("/search")
    public String searchForProducts(Model model, @SearchSpec Specification<Product> specs)
    {
        List<Product> products = productRepository.findAll(Specification.where(specs));
        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model)
    {
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(Model model, Product product, @RequestParam("file") MultipartFile file, @RequestParam("dropDownList1") Integer brandId, @RequestParam("dropDownList2") Integer categoryId) throws IOException
    {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path upload = Paths.get(uploadDirectory);
        if(!Files.exists(upload))
        {
            Files.createDirectories(upload);
        }

        InputStream inputStream = file.getInputStream();
        Path filePath = upload.resolve(filename);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        product.setImage(filename);
        //brandRepository.save(brand);
        product.setBrandId(brandId);
        product.setCategoryId(categoryId);
        productRepository.save(product);
        String status = "Product added";
        model.addAttribute("status", status);
        return "addProduct";
    }

    @GetMapping("/productDetails")
    public String productDetails(Model model, @RequestParam(value = "productId") Integer id)
    {
        Optional<Product> product = productRepository.findById(id);
        if(product == null)
            return "error";
        model.addAttribute("product", product.get());
        return "productDetails";
    }

    @GetMapping("/productsInCategory")
    public String productsInCategory(Model model, @RequestParam(value = "categoryId") Integer id)
    {
        List<Category> categories = categoryService.findAll();
        List<Product> products = productService.findAllInCategory(id);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "productsInCategory";
    }

    @GetMapping("/manageProducts")
    public String manageUsers(Model model)
    {
        List<Product> products = productService.findAll();
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "manageProducts";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(Model model, @RequestParam Integer id)
    {
        productRepository.deleteById(id);
        List<Product> products = productService.findAll();
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "manageProducts";
    }
}
