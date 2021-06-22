package com.example.jwtAuth.repositories;

import com.example.jwtAuth.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByDeletedAtIsNull();
    Role findByIdAndDeletedAtIsNull(Long id);
}
