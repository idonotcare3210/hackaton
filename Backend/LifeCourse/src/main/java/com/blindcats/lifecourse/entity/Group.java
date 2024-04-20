package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long groupID;
    @NotNull
    private String groupName;
    @NotNull
    private Long courseNumber;
    @ManyToOne
    @NotNull
    private Department department;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<User> groups = new ArrayList<>();
}
