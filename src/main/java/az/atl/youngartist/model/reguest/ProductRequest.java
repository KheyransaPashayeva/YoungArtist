package az.atl.youngartist.model.reguest;

import az.atl.youngartist.dao.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
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
    @Column(name = "views",nullable = false)
    private int views;
    @Column(name = "likes",nullable = false)
    private int likes;
    @Column(name = "rating",nullable = false)
    private double rating;
    @Column(name = "information",nullable = false)
    private String information;

    private Set<User> likedByUsers = new HashSet<>();
}
