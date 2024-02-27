package com.codecool.trivia.repository;

import com.codecool.trivia.model.entity.RoleEntity;
import com.codecool.trivia.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, RoleName> {
}
