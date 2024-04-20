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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Group> groups = new ArrayList<>();
}
