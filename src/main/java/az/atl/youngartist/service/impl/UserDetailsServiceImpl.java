package az.atl.youngartist.service.impl;

import az.atl.youngartist.dao.entity.User;
import az.atl.youngartist.dao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userService.findByUsername(username).orElseThrow();
    }



}
