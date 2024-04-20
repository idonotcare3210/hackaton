package com.blindcats.lifecourse.repository;

import com.blindcats.lifecourse.entity.Post;
import com.blindcats.lifecourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPublicationDateLike(Date publicationDate);
    List<Post> findByPublicationTimeLike(String publicationTime);
    List<Post> findByAuthorLike(User author);
    List<Post> findByPostContentLike(String postContent);
}
