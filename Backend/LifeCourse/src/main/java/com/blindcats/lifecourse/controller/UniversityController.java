package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.entity.InstitutionRole;
import com.blindcats.lifecourse.entity.InstitutionalMembersList;
import com.blindcats.lifecourse.entity.User;
import com.blindcats.lifecourse.service.InstitutionRoleService;
import com.blindcats.lifecourse.service.InstitutionService;
import com.blindcats.lifecourse.service.InstitutionalMembersListService;
import com.blindcats.lifecourse.service.UserService;
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
    private UserService userService;
    @Autowired
    private InstitutionRoleService institutionRoleService;
    @Autowired
    private InstitutionalMembersListService institutionalMembersListService;

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
    // Добавьте здесь другие методы, которые вам нужны, например, для добавления или удаления ролей
}
