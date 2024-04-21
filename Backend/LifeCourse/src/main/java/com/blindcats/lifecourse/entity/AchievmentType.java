package com.blindcats.lifecourse.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_achievment_type")
public class AchievmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long typeID;
    private String typeName;
    private Long mark;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achievmentType")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Achievment> achievments = new ArrayList<>();
}
