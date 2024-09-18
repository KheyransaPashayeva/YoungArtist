package az.atl.youngartist.controller;

import az.atl.youngartist.dao.entity.Product;
import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.model.dto.LoginDto;
import az.atl.youngartist.model.dto.ProductDto;
import az.atl.youngartist.model.dto.UserDto;
import az.atl.youngartist.model.enums.Role;
import az.atl.youngartist.model.reguest.UserRequest;
import az.atl.youngartist.model.response.JwtResponse;
import az.atl.youngartist.security.jwt.JwtUtil;
import az.atl.youngartist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final JwtUtil jwtUtil;
private final AuthenticationManager authenticationManager;
    private final UserService userService;

    // Find user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        User user = userService.findByUserName(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUserId(id));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> createUser(@RequestBody UserRequest userRequest) {
       JwtResponse jwtResponse= userService.register(userRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    // Update user role
    @PostMapping("/updateRole")
    public ResponseEntity<User> updateUserRole(@RequestParam Long id, @RequestParam Role role) {
        User updatedUser = userService.updateUserRole(id, role);
        return ResponseEntity.ok(updatedUser);
    }

    // New login endpoint
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginDto loginDto) {
        User authenticatedUser = userService.login(loginDto);
        return ResponseEntity.ok(authenticatedUser);
        }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
           return ResponseEntity.ok().build();
    }
@PutMapping("/{id}")
public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        userService.updateUser(id,userRequest);
        return ResponseEntity.ok().build();
}
    @GetMapping("/{id}/liked-products")
    public ResponseEntity<Set<ProductDto>> getLikedProducts(@PathVariable Long id) {
        Set<Product> likedProducts = userService.findLikeProduct(id);

        // Convert to DTO if necessary
        Set<ProductDto> likedProductDtos = likedProducts.stream()
                .map(ProductDto::new)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(likedProductDtos);
    }
}
