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

    public Institution getInstitution() {
        return institution;
    }

    public InstitutionRole getInstitutionRole() {
        return institutionRole;
    }

    public Long getMemberID() {
        return memberID;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public void setMemberID(Long memberID) {
        this.memberID = memberID;
    }

    public void setInstitutionRole(InstitutionRole institutionRole) {
        this.institutionRole = institutionRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
