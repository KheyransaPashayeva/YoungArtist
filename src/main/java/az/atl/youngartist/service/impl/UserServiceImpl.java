package az.atl.youngartist.service.impl;

import az.atl.youngartist.dao.entity.Product;
import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.dao.repository.UserRepository;
import az.atl.youngartist.exception.UserNotFoundException;
import az.atl.youngartist.mapper.UserMapper;
import az.atl.youngartist.model.dto.LoginDto;
import az.atl.youngartist.model.dto.UserDto;
import az.atl.youngartist.model.enums.Role;
import az.atl.youngartist.model.reguest.UserRequest;
import az.atl.youngartist.model.response.JwtResponse;
import az.atl.youngartist.security.jwt.JwtUtil;
import az.atl.youngartist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private  final BCryptPasswordEncoder encoder;
    private  final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException("user not found"));
    }

    @Override
    public JwtResponse register(UserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = User
                .builder()
                .username(userRequest.getUsername())
                .password(encoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .build();
              if (userRequest.getUserRole() != null) {
                 user.setUserRole(userRequest.getUserRole());
        } else {
            user.setUserRole(Role.USER); // Default to USER if no role is selected
        }

        user.setUserRole(Role.USER);
        userRepository.save(user);
        String jwtToken = jwtUtil.generateToken(user);
        return new JwtResponse(jwtToken);

    }

    @Override
    public User updateUserRole(Long id, Role role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Update the role
        user.setUserRole(role);

        return userRepository.save(user);

    }

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid email or password"));

        // Check if password matches
        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
       return  user;

    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Update the role
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(encoder.encode(userRequest.getPassword()));

    }

    @Override
    public UserDto findByUserId(Long id) {
      return   userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

    }

    @Override
    public Set<Product> findLikeProduct(Long id) {
            User user = userRepository.findByIdWithLikedProducts(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            return user.getLikedProducts();
        }
}
