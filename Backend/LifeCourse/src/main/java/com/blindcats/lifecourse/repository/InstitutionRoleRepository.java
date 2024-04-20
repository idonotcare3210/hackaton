package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.InstitutionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionRoleRepository extends JpaRepository<InstitutionRole, Long> {
    InstitutionRole findByInstitutionRoleName(String institutionRoleName);
}