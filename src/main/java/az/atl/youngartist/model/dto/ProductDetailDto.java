package az.atl.youngartist.model.dto;

import az.atl.youngartist.dao.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
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
public class ProductDetailDto {
    @Id
    private Long id;
    @Column(name = "product_name",nullable = false)
    private String name;
    @Column(name = "product_price",nullable = false)
    private BigDecimal price;
    @Column(name = "author",nullable = false)
    private String author;
    @Column(name = "imageUrl",nullable = false)
    private String imageUrl;
    @Column(name = "likes",nullable = false)
    private int likes;

}
