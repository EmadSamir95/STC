package com.stc.systemdesign.security.service;

import com.stc.systemdesign.entity.UserEntity;
import com.stc.systemdesign.exception.UserNameNotFoundException;
import com.stc.systemdesign.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.build(loadUser(username));
    }

    private UserEntity loadUser(String username){
        return userRepo.findByUsernameIgnoreCase(username).orElseThrow(() -> new UserNameNotFoundException("no user found with username " + username));
    }
}
