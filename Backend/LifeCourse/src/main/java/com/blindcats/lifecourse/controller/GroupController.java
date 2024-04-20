package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.entity.Department;
import com.blindcats.lifecourse.entity.Group;
import com.blindcats.lifecourse.service.DepartmentService;
import com.blindcats.lifecourse.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/university/{institutionId}/faculty/{facultyId}/department/{departmentId}/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/add")
    public String addGroup(@PathVariable("institutionId") Long institutionId,
                           @PathVariable("facultyId") Long facultyId,
                           @PathVariable("departmentId") Long departmentId,
                           @RequestParam("groupName") String groupName,
                           @RequestParam("courseNumber") Long courseNumber, // Добавляем номер курса в параметры
                           Model model) {
        Department department = departmentService.getDepartmentById(departmentId).getBody();
        Group group = new Group();
        group.setGroupName(groupName);
        group.setCourseNumber(courseNumber); // Устанавливаем номер курса
        group.setDepartment(department);
        groupService.save(group);

        return "redirect:/university/" + institutionId + "/faculty/" + facultyId + "/department/" + departmentId;
    }

    @PutMapping("/edit/{groupId}")
    public String editGroup(@PathVariable("institutionId") Long institutionId,
                            @PathVariable("facultyId") Long facultyId,
                            @PathVariable("departmentId") Long departmentId,
                            @PathVariable("groupId") Long groupId,
                            @RequestParam("groupName") String groupName,
                            @RequestParam("courseNumber") Long courseNumber, // Добавляем номер курса в параметры
                            Model model) {
        Group group = groupService.getGroupById(groupId).getBody();
        group.setGroupName(groupName);
        group.setCourseNumber(courseNumber); // Обновляем номер курса
        groupService.updateGroup(groupId, group);

        return "redirect:/university/" + institutionId + "/faculty/" + facultyId + "/department/" + departmentId;
    }

    @DeleteMapping("/delete/{groupId}")
    public String deleteGroup(@PathVariable("institutionId") Long institutionId,
                              @PathVariable("facultyId") Long facultyId,
                              @PathVariable("departmentId") Long departmentId,
                              @PathVariable("groupId") Long groupId,
                              Model model) {
        groupService.deleteGroup(groupId);

        return "redirect:/university/" + institutionId + "/faculty/" + facultyId + "/department/" + departmentId;
    }

    // Добавьте здесь другие методы, которые вам нужны
}
