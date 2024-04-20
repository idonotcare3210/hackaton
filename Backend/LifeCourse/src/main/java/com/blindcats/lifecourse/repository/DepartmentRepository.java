package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Department;
import com.blindcats.lifecourse.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByDepartmentNameLike(String departmentName);
    List<Department> findByFacultyLike(Faculty faculty);
}