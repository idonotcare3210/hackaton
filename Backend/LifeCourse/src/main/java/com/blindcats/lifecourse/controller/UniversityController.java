package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.entity.*;
import com.blindcats.lifecourse.repository.DepartmentRepository;
import com.blindcats.lifecourse.repository.FacultyRepository;
import com.blindcats.lifecourse.repository.GroupRepository;
import com.blindcats.lifecourse.repository.InstitutionRepository;
import com.blindcats.lifecourse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UniversityController {
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private InstitutionRoleService institutionRoleService;
    @Autowired
    private InstitutionalMembersListService institutionalMembersListService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;
    @GetMapping("/university")
    public String institutionList(Model model) {
        model.addAttribute("allInstitutions", institutionService.getAllInstitutions());
        return "university";
    }

    @PostMapping("/university")
    public String deleteInstitution(@RequestParam(required = true, defaultValue = "" ) Long institutionId,
                                    @RequestParam(required = true, defaultValue = "" ) String action,
                                    Model model) {
        if (action.equals("delete")){
            institutionService.deleteInstitution(institutionId);
        }
        return "redirect:/university";
    }

    @GetMapping("/university/gt/{institutionId}")
    public String gtInstitution(@PathVariable("institutionId") Long institutionId, Model model) {
        model.addAttribute("institution", institutionService.getInstitutionById(institutionId));
        return "university";
    }
    @PostMapping("/university/addMember/{institutionId}")
    public String addInstitutionMember(@PathVariable("institutionId") Long institutionId,
                                       @RequestParam("userId") Long userId,
                                       @RequestParam("roleId") Long roleId,
                                       Model model) {
        ResponseEntity<Institution> response = institutionService.getInstitutionById(institutionId);
        Institution institution = response.getBody();
        User user = userService.findUserById(userId);
        InstitutionRole role = institutionRoleService.getRoleById(roleId);

        InstitutionalMembersList member = new InstitutionalMembersList();
        member.setInstitution(institution);
        member.setUser(user);
        member.setInstitutionRole(role);

        institutionalMembersListService.save(member);

        return "redirect:/university";
    }
    @GetMapping("/{institutionId}/faculties")
    public String getFacultiesByInstitution(@PathVariable("institutionId") Long institutionId, Model model) {
        Institution institution = institutionRepository.findById(institutionId).orElseThrow(() -> new RuntimeException("Institution not found"));
        List<Faculty> faculties = facultyService.getFacultiesByInstitution(institution);
        model.addAttribute("faculties", faculties);
        return "faculties";
    }

    @GetMapping("/{institutionId}/faculty/{facultyId}/departments")
    public String getDepartmentsByFaculty(@PathVariable("facultyId") Long facultyId, Model model) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new RuntimeException("Faculty not found"));
        List<Department> departments = departmentService.getDepartmentsByFaculty(faculty);
        model.addAttribute("departments", departments);
        return "departments";
    }

    @GetMapping("/{institutionId}/faculty/{facultyId}/department/{departmentId}/groups")
    public String getGroupsByDepartment(@PathVariable("departmentId") Long departmentId, Model model) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Faculty not found"));
        List<Group> groups = groupService.getGroupsByDepartment(department);
        model.addAttribute("groups", groups);
        return "groups";
    }
    @PostMapping("/user/{userId}/changeGroup")
    public String changeUserGroup(@PathVariable("userId") Long userId, @RequestParam("groupId") Long groupId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            model.addAttribute("message", "Пользователь не найден");
            return "error";
        }

        Group group = groupService.findGroupById(groupId);
        if (group == null) {
            model.addAttribute("message", "Группа не найдена");
            return "error";
        }

        user.setGroup(group);
        userService.saveUser(user);

        return "redirect:/user/" + userId;
    }
    // Добавьте здесь другие методы, которые вам нужны, например, для добавления или удаления ролей
}
