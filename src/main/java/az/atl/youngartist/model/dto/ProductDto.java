package az.atl.youngartist.model.dto;

import az.atl.youngartist.dao.entity.Product;
import az.atl.youngartist.dao.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Id
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

    private Set<User> likedByUsers = new HashSet<>();

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.author = product.getAuthor();
        this.size = product.getSize();
        this.imageUrl = product.getImageUrl();
        this.likes = product.getLikes();
        this.rating = product.getRating();
        this.information = product.getInformation();
    }

}

