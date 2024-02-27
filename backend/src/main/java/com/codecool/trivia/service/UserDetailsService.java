package com.codecool.trivia.service;

import com.codecool.trivia.model.entity.Role;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import com.codecool.trivia.security.jwt.AuthEntryPointJwt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserDetailsService {
    private final UserRepository userRepository;
    private final AuthEntryPointJwt unauthorizedHandler;

    public UserDetailsService(UserRepository userRepository, AuthEntryPointJwt unauthorizedHandler) {
        this.userRepository = userRepository;
        this.unauthorizedHandler = unauthorizedHandler;
    }

    public void addRoleFor(TriviaUser triviaUser, Role role) {
        TriviaUser triviaUser1 = userRepository.findTriviaUserByName(triviaUser.getName());
        Set<Role> oldRoles = triviaUser1.getRoles();

        Set<Role> copiedRoles = new HashSet<>(oldRoles);
        copiedRoles.add(role);

        userRepository.save(triviaUser1);
    }
}
