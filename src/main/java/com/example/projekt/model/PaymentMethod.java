package com.example.projekt.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer methodId;
    @Size(min = 3, max = 255)
    private String name;

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
