package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Institution findByInstitutionName(String institutionName);
    List<Institution> findByInstitutionSiteLike(String institutionSite);
    List<Institution> findByInstitutionAddressLike(String institutionAddress);
    List<Institution> findByAgentLike(User agent);
}