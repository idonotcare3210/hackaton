package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Department;
import com.blindcats.lifecourse.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByGroupNameLike(String groupName);
    List<Group> findByCourseNumberLike(Long courseNumber);
    List<Group> findByDepartmentLike(Department department);
    List<Group> findByDepartment(Department department);
}