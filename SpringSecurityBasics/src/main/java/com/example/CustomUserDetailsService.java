package com.example;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return new UsersInfo();
        return new User("rohan2", "rohan2", Arrays.asList(new SimpleGrantedAuthority(  "user")));
    }

//    static class UsersInfo implements UserDetails, Serializable {
//
//        private static final long serialVersionUID = 6679435153583838037L;
//
//        private String username = "rohan2";
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + "user"));
//        }
//
//        @Override
//        public String getPassword() {
//            return "rohan2";
//        }
//
//        @Override
//        public String getUsername() {
//            return "rohan2";
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//
//
//        // to enable session management maximum
//        @Override
//        public boolean equals(Object obj) {
//            if (obj instanceof UsersInfo) {
//                return username.equals(((UsersInfo) obj).getUsername());
//            }
//            return false;
//        }
//
//        @Override
//        public int hashCode() {
//            return username != null ? username.hashCode() : 0;
//        }
//    }

}
