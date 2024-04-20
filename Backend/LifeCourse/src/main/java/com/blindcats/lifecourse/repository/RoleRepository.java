package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
