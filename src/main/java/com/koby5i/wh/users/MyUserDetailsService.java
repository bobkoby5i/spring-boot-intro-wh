package com.koby5i.wh.users;

import com.koby5i.wh.users.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return new MyUserDetails(username);
        //fetch user from DB
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found."));

        //convert jpa user object into MyUserDetails object and return
        return user.map(MyUserDetails::new).get();
    }
}
