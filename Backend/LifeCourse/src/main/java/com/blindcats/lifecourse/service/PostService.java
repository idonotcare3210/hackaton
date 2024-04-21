package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.Post;
import com.blindcats.lifecourse.entity.User;
import com.blindcats.lifecourse.repository.PostRepository;
import com.blindcats.lifecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Post createPost(String content, User author) {
        Post post = new Post();
        post.setPostContent(content);
        post.setAuthor(author);
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        post.setPublicationDate(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        post.setPublicationTime(LocalTime.now().format(formatter));
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, String content) {
        Optional<Post> postFromDb = postRepository.findById(postId);
        if (postFromDb.isPresent()) {
            Post post = postFromDb.get();
            post.setPostContent(content);
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
