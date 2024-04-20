package com.blindcats.lifecourse.entity;

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
    @OneToMany(mappedBy = "achievmentType")
    // По умолчанию пустой список помогает не проверять на null
    // Выборка данных выполнится при обращении к getPosts()
    private List<Achievment> achievments = new ArrayList<>();
}
