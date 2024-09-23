package az.atl.youngartist.controller;

import az.atl.youngartist.model.dto.ProductDetailDto;
import az.atl.youngartist.model.dto.ProductDto;
import az.atl.youngartist.model.reguest.ProductRequest;
import az.atl.youngartist.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Validated
@RequestMapping("/api/v1/product")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDetailDto>> getAll(){
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductRequest productRequest){
        productService.createPproduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,@RequestBody ProductRequest productRequest){
        productService.update(id,productRequest);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{productId}/like")
    public ResponseEntity<ProductDto> likeProduct(@RequestParam Long userId, @PathVariable Long productId) {
        ProductDto product = productService.like(userId, productId);
        return ResponseEntity.ok(product);
    }
    @PostMapping("/{productId}/addBasket")
    public ResponseEntity<ProductDto> addToBasket(@PathVariable Long productId) {
        ProductDto product = productService.addBasket(productId);
        return ResponseEntity.ok(product);
    }
}
