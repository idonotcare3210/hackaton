package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long facultyID;
    @NotNull
    private String facultyName;
    @ManyToOne
    @NotNull
    private Institution institution;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Department> departments = new ArrayList<>();

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Institution getInstitution() {
        return institution;
    }

    public Long getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(Long facultyID) {
        this.facultyID = facultyID;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
