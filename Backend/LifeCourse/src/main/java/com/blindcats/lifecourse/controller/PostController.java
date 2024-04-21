package com.blindcats.lifecourse.controller;

import com.blindcats.lifecourse.entity.Post;
import com.blindcats.lifecourse.entity.User;
import com.blindcats.lifecourse.repository.UserRepository;
import com.blindcats.lifecourse.service.PostService;
import com.blindcats.lifecourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Post createPost(@RequestParam String content, @RequestParam Long authorId) {
        // Получаем текущего пользователя из контекста безопасности
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // Находим пользователя в базе данных
        User author = userRepository.findByUsername(username);
        if (author == null) {
            return null; // Или выбросьте исключение
        }
        return postService.createPost(content, author);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestParam String content) {
        return postService.updatePost(postId, content);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }
}
