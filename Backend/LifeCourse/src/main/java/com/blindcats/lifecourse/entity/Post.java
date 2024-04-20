package com.blindcats.lifecourse.entity;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_post")
public class Post {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postID;
    @Size(max =  3000)
    private String postContent;
    @NotNull
    private Date publicationDate;
    @NotNull
    private String publicationTime;
    @ManyToOne
    @NotNull
    private User author;
}
