package com.barta.myanimelounge.repository;

import java.util.Optional;

import com.barta.myanimelounge.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barta.myanimelounge.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
