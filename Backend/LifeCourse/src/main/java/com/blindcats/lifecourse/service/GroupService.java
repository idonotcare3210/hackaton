package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.Department;
import com.blindcats.lifecourse.entity.Group;
import com.blindcats.lifecourse.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Group save(Group group) {
        return groupRepository.save(group);
    }
    public ResponseEntity<Group> getGroupById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found for this id :: " + id));
        return ResponseEntity.ok().body(group);
    }
    public Group findGroupById(Long groupId) {
        Optional<Group> groupFromDb = groupRepository.findById(groupId);
        return groupFromDb.orElse(null);
    }

    public List<Group> getGroupsByDepartment(Department department) {
        return groupRepository.findByDepartment(department);
    }

    public ResponseEntity<Group> updateGroup(Long id, Group groupDetails) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found for this id :: " + id));
        group.setGroupName(groupDetails.getGroupName());
        group.setCourseNumber(groupDetails.getCourseNumber()); // Обновляем номер курса
        final Group updatedGroup = groupRepository.save(group);
        return ResponseEntity.ok(updatedGroup);
    }

    public ResponseEntity<?> deleteGroup(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found for this id :: " + id));
        groupRepository.delete(group);
        return ResponseEntity.ok().build();
    }
}
