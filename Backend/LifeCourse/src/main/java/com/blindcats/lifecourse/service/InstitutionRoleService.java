package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.InstitutionRole;
import com.blindcats.lifecourse.repository.InstitutionRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionRoleService {
    @Autowired
    private InstitutionRoleRepository institutionRoleRepository;

    public InstitutionRole getRoleById(Long id) {
        return institutionRoleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found for this id :: " + id));
    }

    // Добавьте здесь другие методы, которые вам нужны
}
