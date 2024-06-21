package com.d.una.app.back.business;

import com.d.una.app.back.config.MessageLoader;
import com.d.una.app.back.exception.BusinessRuleException;
import com.d.una.app.back.model.User;
import com.d.una.app.back.repository.IUserRepository;
import com.d.una.app.back.util.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM008, MessageLoader.getInstance().getMessage(MessagesConstants.EM008, username)));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .toList();
    }
}
