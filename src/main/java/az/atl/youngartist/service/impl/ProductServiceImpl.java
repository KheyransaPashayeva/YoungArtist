package az.atl.youngartist.service.impl;

import az.atl.youngartist.dao.entity.Product;
import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.dao.repository.ProductRepository;
import az.atl.youngartist.dao.repository.UserRepository;
import az.atl.youngartist.exception.ProductNotFoundException;
import az.atl.youngartist.exception.UserNotFoundException;
import az.atl.youngartist.mapper.ProductMapper;
import az.atl.youngartist.model.dto.ProductDetailDto;
import az.atl.youngartist.model.dto.ProductDto;
import az.atl.youngartist.model.reguest.ProductRequest;
import az.atl.youngartist.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImpl  implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;
    @Override
    public List<ProductDetailDto> findAllProduct() {
        return productRepository.findAll()
                .stream().map(productMapper::toDetailDto)
                .toList();
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id).map(productMapper::toDto)
                .orElseThrow(()->new ProductNotFoundException("id not found "));
    }

    @Override
    public void createPproduct(ProductRequest productRequest) {
        Product product= productMapper.toEntity(productRequest);
        productRepository.save(product);
    }

    @Override
    public void update(Long id, ProductRequest productRequest) {
        Product product=productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("id nout found"));
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setAuthor(product.getAuthor());
        product.setInformation(productRequest.getInformation());
        product.setImageUrl(productRequest.getImageUrl());
        product.setRating(productRequest.getRating());
        product.setLikes(0);
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
       productRepository.findById(id).ifPresent(product -> productRepository.deleteById(id));
    }

    @Override
    public ProductDto like(Long userId,Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        // Add product to user's liked products
        user.getLikedProducts().add(product);
        userRepository.save(user);

        // Update product's like count
        product.setLikes(product.getLikes() + 1);
        productRepository.save(product);

        return productMapper.toDto(product);

    }

    @Override
    public ProductDto addBasket(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapper.toDto(product);

    }
}
