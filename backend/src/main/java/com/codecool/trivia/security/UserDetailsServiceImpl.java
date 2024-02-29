package com.codecool.trivia.security;

import com.codecool.trivia.model.entity.Role;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import com.codecool.trivia.security.jwt.AuthEntryPointJwt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private final AuthEntryPointJwt unauthorizedHandler;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository, AuthEntryPointJwt unauthorizedHandler) {
    this.userRepository = userRepository;
    this.unauthorizedHandler = unauthorizedHandler;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    TriviaUser triviaUser = userRepository.findTriviaUserByName(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

    List<SimpleGrantedAuthority> roles = new ArrayList<>();
    for (Role role : triviaUser.getRoles()) {
      roles.add(new SimpleGrantedAuthority(role.toString()));
    }

    return new User(triviaUser.getName(), triviaUser.getPassword(), roles);
  }
}
