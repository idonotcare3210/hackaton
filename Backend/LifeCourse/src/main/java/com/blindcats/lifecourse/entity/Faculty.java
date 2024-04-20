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
}
