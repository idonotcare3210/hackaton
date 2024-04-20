package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.Department;
import com.blindcats.lifecourse.entity.Faculty;
import com.blindcats.lifecourse.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department save(Department department) {
        return departmentRepository.save(department);
    }
    public List<Department> getDepartmentsByFaculty(Faculty faculty) {
        return departmentRepository.findByFaculty(faculty);
    }
    public ResponseEntity<Department> getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Faculty not found for this id :: " + id));
        return ResponseEntity.ok().body(department);
    }
    public ResponseEntity<Department> updateDepartment(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found for this id :: " + id));
        department.setDepartmentName(departmentDetails.getDepartmentName());
        final Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    public ResponseEntity<?> deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found for this id :: " + id));
        departmentRepository.delete(department);
        return ResponseEntity.ok().build();
    }
}
