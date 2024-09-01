package az.atl.youngartist.service;

import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.model.dto.LoginDto;
import az.atl.youngartist.model.dto.UserDto;
import az.atl.youngartist.model.enums.Role;
import az.atl.youngartist.model.reguest.UserRequest;

import java.util.List;

public interface UserService {
    User findByUserName(String username);
     void register(UserRequest userRequest);
    User updateUserRole(Long id, Role role);
   User login(LoginDto loginDto);
   void deleteUser(Long id);
   void updateUser(Long id,UserDto userDto);
   UserDto findById(Long id);
}
