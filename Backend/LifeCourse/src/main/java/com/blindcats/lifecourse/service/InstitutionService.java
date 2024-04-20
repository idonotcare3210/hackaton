package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.entity.User;
import com.blindcats.lifecourse.repository.InstitutionRepository;
import com.blindcats.lifecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private UserRepository userRepository;

    public Institution createInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    public ResponseEntity<Institution> getInstitutionById(Long id) {
        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new RuntimeException("Institution not found for this id :: " + id));
        return ResponseEntity.ok().body(institution);
    }

    public ResponseEntity<Institution> updateInstitution(Long id, Institution institutionDetails) {
        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new RuntimeException("Institution not found for this id :: " + id));
        institution.setInstitutionName(institutionDetails.getInstitutionName());
        institution.setInstitutionSite(institutionDetails.getInstitutionSite());
        institution.setInstitutionAddress(institutionDetails.getInstitutionAddress());
        // Получаем текущего пользователя из контекста безопасности
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // Находим пользователя в базе данных
        User currentUser = userRepository.findByUsername(username);
        // Устанавливаем текущего пользователя как агента
        institution.setAgent(currentUser);
        final Institution updatedInstitution = institutionRepository.save(institution);
        return ResponseEntity.ok(updatedInstitution);
    }

    public ResponseEntity<?> deleteInstitution(Long id) {
        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new RuntimeException("Institution not found for this id :: " + id));
        institutionRepository.delete(institution);
        return ResponseEntity.ok().build();
    }
}
