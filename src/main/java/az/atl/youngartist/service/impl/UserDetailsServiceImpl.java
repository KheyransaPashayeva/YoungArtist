package az.atl.youngartist.service.impl;

import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import az.atl.youngartist.dao.entity.User;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new NoSuchElementException("User with email " + username + " not found"));

        return user;
       //if (user != null) {
       //    // If getUserRole() is an enum, convert it to a String
       //    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
       //    authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name())); // Assuming user role is an Enum

       //    return userRepository.findByUsername(username).orElseThrow();
       //}
       //return null;
    }

}
