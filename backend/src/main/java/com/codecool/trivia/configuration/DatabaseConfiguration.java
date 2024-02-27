package com.codecool.trivia.configuration;

import com.codecool.trivia.model.entity.Role;
import com.codecool.trivia.model.enums.RoleName;
import com.codecool.trivia.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
  private final RoleRepository roleRepository;

  @Autowired
  public DatabaseConfiguration(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Bean
  public CommandLineRunner initializeRoles() {
    return args -> {
      for (RoleName role : RoleName.values()) {
        roleRepository.save(new Role(role));
      }
    };
  }
}
