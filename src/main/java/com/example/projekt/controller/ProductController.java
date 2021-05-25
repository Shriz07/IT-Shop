package com.example.projekt.controller;

import com.example.projekt.model.Brand;
import com.example.projekt.model.Category;
import com.example.projekt.model.Product;
import com.example.projekt.service.*;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ProductController
{
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/product_images";

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/search")
    public String searchForProducts(Model model, @SearchSpec Specification<Product> specs)
    {
        List<Product> products = productService.findAllWithSpecs(specs);
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

        productService.addProduct(product, filename, brandId, categoryId);
        String status = "Product added";
        model.addAttribute("status", status);
        return "addProduct";
    }

    @GetMapping("/productDetails")
    public String productDetails(Model model, @RequestParam(value = "productId") Integer id)
    {
        Product product = productService.findById(id);
        if(product == null)
            return "error";
        model.addAttribute("product", product);
        model.addAttribute("specifications", product.getSpecifications());
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

    @GetMapping("/modifyProduct")
    public String modifyProduct(Model model, @RequestParam(value = "productId") Integer productId)
    {
        Product product = productService.findById(productId);
        List<Category> categories = categoryService.findAll();
        List<Brand> brands = brandService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        model.addAttribute("id", productId);
        return "modifyProduct";
    }

    @PostMapping("/modifyProduct")
    public void modifyProduct(HttpServletResponse response, Product product, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "dropDownList1") Integer brandId, @RequestParam(value = "dropDownList2") Integer categoryId) throws IOException
    {
        if(brandId == -1)
            brandId = product.getBrandId();
        if(categoryId == -1)
            categoryId = product.getCategoryId();
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if(filename.length() > 4) {
            Path upload = Paths.get(uploadDirectory);
            if (!Files.exists(upload))
                Files.createDirectories(upload);

            InputStream inputStream = file.getInputStream();
            Path filePath = upload.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            product.setImage(filename);
        }
        else
        {
            Product prod = productService.findById(product.getProductId());
            product.setImage(prod.getImage());
        }
        productService.updateProduct(product, categoryId, brandId);

        response.sendRedirect("/manageProducts");
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(Model model, @RequestParam Integer id)
    {
        productService.deleteById(id);
        List<Product> products = productService.findAll();
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("categories", categories);
        return "manageProducts";
    }
}
