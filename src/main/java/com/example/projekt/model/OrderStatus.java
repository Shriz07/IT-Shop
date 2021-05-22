package com.example.projekt.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "order_statuses")
public class OrderStatus
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer statusId;
    @Size(min = 3, max = 255)
    private String name;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
