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
    private Long id;
    private String name;
    private BigDecimal price;
    private String author;
    private String size;
    private String imageUrl;
    private int views;
    private int likes;
    private double rating;
    private String information;

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

