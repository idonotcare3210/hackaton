package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.entity.Faculty;
import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.service.FacultyService;
import com.blindcats.lifecourse.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/university/{institutionId}/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private InstitutionService institutionService;

    @PostMapping("/add")
    public String addFaculty(@PathVariable("institutionId") Long institutionId,
                             @RequestParam("facultyName") String facultyName,
                             Model model) {
        Institution institution = institutionService.getInstitutionById(institutionId).getBody();
        if (institution == null) {
            throw new RuntimeException("Institution not found for this id :: " + institutionId);
        }
        Faculty faculty = new Faculty();
        faculty.setFacultyName(facultyName);
        faculty.setInstitution(institution);
        facultyService.save(faculty);

        return "redirect:/university/" + institutionId;
    }
    @PutMapping("/edit/{facultyId}")
    public String editFaculty(@PathVariable("facultyId") Long facultyId,
                              @RequestParam("facultyName") String facultyName,
                              Model model) {
        Faculty faculty = facultyService.getFacultyById(facultyId).getBody();
        faculty.setFacultyName(facultyName);
        facultyService.updateFaculty(facultyId, faculty);
        return "redirect:/university";
    }

    @DeleteMapping("/delete/{facultyId}")
    public String deleteFaculty(@PathVariable("facultyId") Long facultyId, Model model) {
        facultyService.deleteFaculty(facultyId);
        return "redirect:/university";
    }

    // Добавьте здесь другие методы, которые вам нужны
}
