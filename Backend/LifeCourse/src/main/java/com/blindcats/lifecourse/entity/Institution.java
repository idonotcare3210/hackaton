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
    @OneToMany(mappedBy = "institution")
    private List<InstitutionalMembersList> membersLists = new ArrayList<>();
    @OneToMany(mappedBy = "institution")
    private List<Faculty> faculties = new ArrayList<>();

    public Long getInstitutionID() {
        return institutionID;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public String getInstitutionSite() {
        return institutionSite;
    }

    public List<InstitutionalMembersList> getMembersLists() {
        return membersLists;
    }

    public void setInstitutionID(Long institutionID) {
        this.institutionID = institutionID;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public void setInstitutionSite(String institutionSite) {
        this.institutionSite = institutionSite;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public void setMembersLists(List<InstitutionalMembersList> membersLists) {
        this.membersLists = membersLists;
    }
}
