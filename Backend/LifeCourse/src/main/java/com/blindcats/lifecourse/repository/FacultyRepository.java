package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByFacultyNameLike(String facultyName);
    List<Faculty> findByInstitutionLike(Institution institution);
}