package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_institutional_member")
public class InstitutionalMembersList {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberID;
    @ManyToOne
    @NotNull
    private Institution institution;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
    @ManyToOne
    @NotNull
    private InstitutionRole institutionRole;
}
