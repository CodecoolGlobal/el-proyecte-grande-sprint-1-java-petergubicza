package com.codecool.trivia.dto.frontend_request.user;

import com.codecool.trivia.model.entity.Role;

import java.util.Set;

public record UserRolesDTO(String name, Set<Role> roles) {
}
