package com.udaan.security;

import com.udaan.entity.User;
import com.udaan.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomeUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(useremail);

        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                useremail,
                user.getPassword(),
                authorities
        );
    }
}
