package com.example.projekt.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer productId;
    @Size(min = 3, max = 255)
    private String name;
    private Integer brandId;
    private float price;
    @Size(min = 3, max = 4096)
    private String description;
    @Size(min = 3, max = 255)
    private String image;
    private Integer inStock;
    private Integer categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId", insertable = false, updatable = false)
    private Brand brand;

    public Integer getProductId() { return productId; }

    public void setProductId(Integer productId) { this.productId = productId; }

    public String getName()
    {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public Integer getBrandId() { return brandId; }

    public void setBrandId(Integer brandId) { this.brandId = brandId; }

    public float getPrice() { return price; }

    public void setPrice(Float price) { this.price = price; }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    public Integer getInStock() { return inStock; }

    public void setInStock(Integer inStock) { this.inStock = inStock; }

    public Integer getCategoryId() { return categoryId; }

    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public String getCategory() { return category.getName(); }

    public String getBrand() { return brand.getName(); }
}
