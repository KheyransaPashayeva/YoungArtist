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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name",nullable = false)
    private String name;
    @Column(name = "product_price",nullable = false)
    private BigDecimal price;
    @Column(name = "author",nullable = false)
    private String author;
    @Column(name = "size",nullable = false)
    private String size;
    @Column(name = "imageUrl",nullable = false)
    private String imageUrl;
    @Column(name = "likes",nullable = false)
    private int likes;
    @Column(name = "rating",nullable = false)
    private double rating;
    @Column(name = "information",nullable = false)
    private String information;
    @ManyToMany(mappedBy = "likedProducts")
    private Set<User> likedByUsers = new HashSet<>();
}

