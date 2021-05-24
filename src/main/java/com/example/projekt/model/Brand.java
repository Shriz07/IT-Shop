package com.example.projekt.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer brandId;

    @Size(min = 3, max = 45)
    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand")
    private Set<Product> products = new HashSet<>();

    public Integer getBrandId() { return brandId; }

    public void setBrandId(Integer brandId) { this.brandId = brandId; }

    public String getName()
    {
        return name;
    }

    public void setName(String name) { this.name = name; }

}
