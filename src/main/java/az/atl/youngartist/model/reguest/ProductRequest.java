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
    private String name;
    private BigDecimal price;
    private String author;
    private String size;
    private String imageUrl;
    private int views;
    private int likes;
    private double rating;
    private String information;

}
