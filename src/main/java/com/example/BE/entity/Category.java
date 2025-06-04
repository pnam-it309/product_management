package com.example.BE.entity;

import com.example.BE.entity.base.PrimaryEntity;
import com.example.BE.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends PrimaryEntity implements Serializable{
    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
