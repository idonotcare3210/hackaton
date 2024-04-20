package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.entity.Department;
import com.blindcats.lifecourse.entity.Faculty;
import com.blindcats.lifecourse.service.DepartmentService;
import com.blindcats.lifecourse.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/university/{institutionId}/faculty/{facultyId}/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private FacultyService facultyService;

    @PostMapping("/add")
    public String addDepartment(@PathVariable("institutionId") Long institutionId,
                                @PathVariable("facultyId") Long facultyId,
                                @RequestParam("departmentName") String departmentName,
                                Model model) {
        Faculty faculty = facultyService.getFacultyById(facultyId).getBody();
        if (faculty == null) {
            throw new RuntimeException("Faculty not found for this id :: " + facultyId);
        }
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setFaculty(faculty);
        departmentService.save(department);

        return "redirect:/university/" + institutionId + "/faculty/" + facultyId;
    }
    @PutMapping("/edit/{departmentId}")
    public String editDepartment(@PathVariable("institutionId") Long institutionId,
                                 @PathVariable("facultyId") Long facultyId,
                                 @PathVariable("departmentId") Long departmentId,
                                 @RequestParam("departmentName") String departmentName,
                                 Model model) {
        Department department = departmentService.getDepartmentById(departmentId).getBody();
        department.setDepartmentName(departmentName);
        departmentService.updateDepartment(departmentId, department);

        return "redirect:/university/" + institutionId + "/faculty/" + facultyId;
    }

    @DeleteMapping("/delete/{departmentId}")
    public String deleteDepartment(@PathVariable("institutionId") Long institutionId,
                                   @PathVariable("facultyId") Long facultyId,
                                   @PathVariable("departmentId") Long departmentId,
                                   Model model) {
        departmentService.deleteDepartment(departmentId);

        return "redirect:/university/" + institutionId + "/faculty/" + facultyId;
    }

    // Добавьте здесь другие методы, которые вам нужны
}
