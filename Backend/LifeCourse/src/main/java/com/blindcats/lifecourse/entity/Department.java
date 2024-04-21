package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long departmentID;
    @NotNull
    private String departmentName;
    @ManyToOne
    @NotNull
    private Faculty faculty;
    @OneToMany(mappedBy = "department")
    private List<Group> groups = new ArrayList<>();

    public Long getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Long departmentID) {
        this.departmentID = departmentID;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
