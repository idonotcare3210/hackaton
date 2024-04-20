package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.Faculty;
import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    public List<Faculty> getFacultiesByInstitution(Institution institution) {
        return facultyRepository.findByInstitution(institution);
    }
    public ResponseEntity<Faculty> getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found for this id :: " + id));
        return ResponseEntity.ok().body(faculty);
    }
    public ResponseEntity<Faculty> updateFaculty(Long id, Faculty facultyDetails) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found for this id :: " + id));
        faculty.setFacultyName(facultyDetails.getFacultyName());
        final Faculty updatedFaculty = facultyRepository.save(faculty);
        return ResponseEntity.ok(updatedFaculty);
    }

    public ResponseEntity<?> deleteFaculty(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found for this id :: " + id));
        facultyRepository.delete(faculty);
        return ResponseEntity.ok().build();
    }
    // Добавьте здесь другие методы, которые вам нужны
}
