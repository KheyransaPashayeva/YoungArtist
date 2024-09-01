package az.atl.youngartist.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    @Column(name = "product_name",nullable = false)
    private String name;
    @Column(name = "product_price",nullable = false)
    private BigDecimal price;
    @Column(name = "author",nullable = false)
    private String author;
    private String size;
    private String imageUrl;
    private int views;
    private int likes;
    private double rating;
    private String information;

    @ManyToMany(mappedBy = "likedProducts")
    private Set<User> likedByUsers = new HashSet<>();
}

