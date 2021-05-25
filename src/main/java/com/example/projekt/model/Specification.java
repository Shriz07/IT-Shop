package com.example.projekt.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "specification")
public class Specification
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer specificationId;
    @Size(min = 3, max = 100)
    private String specificationKey;
    @Size(min = 1, max = 100)
    private String specificationValue;

    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    public Integer getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationKey(String key) {
        this.specificationKey = key;
    }

    public String getSpecificationKey() {
        return specificationKey;
    }

    public void setSpecificationValue(String value) {
        this.specificationValue = value;
    }

    public String getSpecificationValue() {
        return specificationValue;
    }

    public void setProductId(Integer productId){
        this.productId = productId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
