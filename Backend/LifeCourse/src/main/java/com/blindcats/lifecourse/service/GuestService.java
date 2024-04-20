package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.Institution;
import com.blindcats.lifecourse.entity.User;
import com.blindcats.lifecourse.repository.InstitutionRepository;
import com.blindcats.lifecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Service
public class GuestService {
    @Autowired
    private UserRepository userRepository;

    /*@NotNull
    private String firstname;
    @NotNull
    private String middlename;
    private String lastname;
    @Column(unique = true)
    @NotNull
    private String email;
    @Column(unique = true)
    @NotNull
    @Size(min=2, message = "Не меньше 5 знаков")
    private String username;
    @NotNull
    @Size(min=2, message = "Не меньше 5 знаков")
    private String password;*/

    public boolean changeUserFirstname (String firstname) {
        // Получаем текущего пользователя из контекста безопасности
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // Находим пользователя в базе данных
        User currentUser = userRepository.findByUsername(username);

        currentUser.setFirstname(firstname);
        userRepository.save(currentUser);
        return true;
    }

    public boolean changeUserMiddlename (String middlename) {
        // Получаем текущего пользователя из контекста безопасности
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // Находим пользователя в базе данных
        User currentUser = userRepository.findByUsername(username);

        currentUser.setMiddlename(middlename);
        userRepository.save(currentUser);
        return true;
    }

    public boolean changeUserLastname (String lastname) {
        // Получаем текущего пользователя из контекста безопасности
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // Находим пользователя в базе данных
        User currentUser = userRepository.findByUsername(username);

        currentUser.setLastname(lastname);
        userRepository.save(currentUser);
        return true;
    }

    public boolean changeUserEmail (String email) {
        // Получаем текущего пользователя из контекста безопасности
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // Находим пользователя в базе данных
        User currentUser = userRepository.findByUsername(username);

        currentUser.setEmail(email);
        userRepository.save(currentUser);
        return true;
    }

    public boolean changeUserUsername (String username) {
        // Получаем текущего пользователя из контекста безопасности
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String UserUsername = userDetails.getUsername();
        // Находим пользователя в базе данных
        User currentUser = userRepository.findByUsername(UserUsername);

        currentUser.setUsername(username);
        userRepository.save(currentUser);
        return true;
    }
}
