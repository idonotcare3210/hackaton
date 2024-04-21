package com.blindcats.lifecourse.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OneToMany(cascade =  CascadeType.ALL, mappedBy = "group")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<User> groups = new ArrayList<>();

    public void setGroups(List<User> groups) {
        this.groups = groups;
    }

    public Long getGroupID() {
        return groupID;
    }

    public List<User> getGroups() {
        return groups;
    }

    public Department getDepartment() {
        return department;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public Long getCourseNumber() {
        return courseNumber;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCourseNumber(Long courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}

