package com.sid.SecurityDemo.service;

import com.sid.SecurityDemo.entity.MyUser;
import com.sid.SecurityDemo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser =  userRepository.findByUsername(username);
        if(myUser == null){
            throw new UsernameNotFoundException("No user found");
        }
        return myUser;
    }
}
