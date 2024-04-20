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
    @ManyToOne
    @NotNull
    private User agent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institution")
    // По умолчанию пустой список помогает не проверять на null
    // Выборка данных выполнится при обращении к getPosts()
    private List<IntstitutionalMembersList> membersLists = new ArrayList<>();
}
