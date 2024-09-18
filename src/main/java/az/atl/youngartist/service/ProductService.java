package az.atl.youngartist.service;

import az.atl.youngartist.model.dto.ProductDetailDto;
import az.atl.youngartist.model.dto.ProductDto;
import az.atl.youngartist.model.reguest.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<ProductDetailDto> findAllProduct();
     ProductDto findById(Long id);
     void createPproduct(ProductRequest productRequest);
     void update(Long id, ProductRequest productRequest);
     void deleteById(Long id);
     ProductDto like(Long userId,Long productId);
     ProductDto addBasket(Long productId);

}
