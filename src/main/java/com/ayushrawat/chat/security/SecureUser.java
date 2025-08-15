package com.ayushrawat.chat.security;


import com.ayushrawat.chat.entity.User;
import com.ayushrawat.chat.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecureUser implements UserDetails {

  private final User user;

  public SecureUser(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> roles = new ArrayList<>();
    for (UserRole role : UserRole.fromBitmask(user.getRole())) {
      roles.add(new SimpleGrantedAuthority(role.name()));
    }
    return roles;
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public String getPassword() {
    return "";
  }

}
