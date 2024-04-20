package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_institution_role")
public class InstitutionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long institutionRoleID;
    @Column(unique = true)
    @NotNull
    private String institutionRoleName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institutionRole")
    // По умолчанию пустой список помогает не проверять на null
    // Выборка данных выполнится при обращении к getPosts()
    private List<IntstitutionalMembersList> membersLists = new ArrayList<>();
}