package az.atl.youngartist.controller;

import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.model.dto.LoginDto;
import az.atl.youngartist.model.enums.Role;
import az.atl.youngartist.model.reguest.UserRequest;
import az.atl.youngartist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // Find user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUserName(@PathVariable String username) {
        User user = userService.findByUserName(username);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) {
        userService.register(userRequest);
        return ResponseEntity.ok().build();
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
}
