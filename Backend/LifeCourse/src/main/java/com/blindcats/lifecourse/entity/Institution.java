package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_institution")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long institutionID;
    @Column(unique = true)
    @NotNull
    private String institutionName;
    private String institutionSite;
    @NotNull
    private String institutionAddress;
    @ManyToOne
    @NotNull
    private User agent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institution")
    private List<InstitutionalMembersList> membersLists = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institution")
    private List<Faculty> faculties = new ArrayList<>();
}
