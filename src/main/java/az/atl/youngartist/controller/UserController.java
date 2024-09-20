package az.atl.youngartist.controller;

import az.atl.youngartist.dao.entity.Product;
import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.model.dto.LoginDto;
import az.atl.youngartist.model.dto.ProductDto;
import az.atl.youngartist.model.dto.UserDto;
import az.atl.youngartist.model.enums.Role;
import az.atl.youngartist.model.reguest.LoginRequest;
import az.atl.youngartist.model.reguest.UserRequest;
import az.atl.youngartist.model.reguest.UserRequestDto;
import az.atl.youngartist.model.response.JwtResponse;
import az.atl.youngartist.service.JwtService;
import az.atl.youngartist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;


    @PreAuthorize("hasRole('USER')") // Find user by username
    @GetMapping("/{username}")
    public ResponseEntity<Optional<User>> findByUserName(@PathVariable String username) {
        Optional<User> user = userService.findByUserName(username);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findByUserId(id));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok( userService.register(userRequest));
    }

    // Update user role
    @PostMapping("/updateRole")
    public ResponseEntity<Void> updateUserRole(@RequestParam Long id, @RequestParam Role role) {
        userService.updateUserRole(id, role);
        return ResponseEntity.ok().build();
    }

    // New login endpoint
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
        }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
           return ResponseEntity.ok().build();
    }
@PutMapping("/{id}")
public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequest){
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
