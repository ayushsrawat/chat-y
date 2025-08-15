package com.ayushrawat.chat.security;

import com.ayushrawat.chat.entity.User;
import com.ayushrawat.chat.repository.UserRepository;
import com.ayushrawat.chat.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthProvider implements AuthenticationProvider {

  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String token = ((JwtAuthToken) authentication).getToken();
    final String username = jwtUtil.extractUsername(token);
    if (username == null) {
      throw new BadCredentialsException("Invalid JWT token");
    }

    User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (!jwtUtil.validateToken(token, user)) {
      throw new BadCredentialsException("JWT token validation failed");
    }

    SecureUser secureUser = new SecureUser(user);
    return new UsernamePasswordAuthenticationToken(
      secureUser,
      null,
      secureUser.getAuthorities()
    );
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthToken.class.isAssignableFrom(authentication);
  }

}