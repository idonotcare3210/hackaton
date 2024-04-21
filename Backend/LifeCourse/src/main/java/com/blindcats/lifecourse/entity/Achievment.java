package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_achievment")
public class Achievment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long achievmentID;
    private String achievmentName;
    @ManyToOne
    @NotNull
    private AchievmentType achievmentType;

    public Long getMark() {
        return this.achievmentType.getMark();
    }
}
