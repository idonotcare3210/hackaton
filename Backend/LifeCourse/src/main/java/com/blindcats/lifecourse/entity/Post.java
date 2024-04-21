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

    public Long getPostID() {
        return postID;
    }

    public String getPostContent() {
        return postContent;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }
}
