package az.atl.youngartist.service;

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

import java.util.List;
import java.util.Set;

public interface UserService {
    User findByUserName(String username);
    User findByEmail(String email);
     JwtResponse register(UserRequest userRequest);
    User updateUserRole(Long id, Role role);
   JwtResponse login(LoginRequest loginRequest);
   void deleteUser(Long id);
   void updateUser(Long id, UserRequestDto userRequest);
   UserDto findByUserId(Long id);
   Set<Product> findLikeProduct(Long id);
}
