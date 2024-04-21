package com.blindcats.lifecourse.service;

import com.blindcats.lifecourse.entity.Role;
import com.blindcats.lifecourse.entity.User;
import com.blindcats.lifecourse.repository.RoleRepository;
import com.blindcats.lifecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    public List<User> loadUserByFirstname(String firstname) throws UsernameNotFoundException {
        List<User> user = userRepository.findByFirstnameLike(firstname);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
    public List<User> loadUserByMiddlename(String middlename) throws UsernameNotFoundException {
        List<User> user = userRepository.findByMiddlenameLike(middlename);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
    public List<User> loadUserByLastname(String lastname) throws UsernameNotFoundException {
        List<User> user = userRepository.findByLastnameLike(lastname);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    public int saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return 1;//пользователь уже существует
        }
        else {
            User userByEmail = userRepository.findByEmail(user.getEmail());
            if (userByEmail != null) {
                return 2;//аккаунт с данной почтой уже существует
            }
        }
        String email = user.getEmail();
        if (!validate(email)) {
            return 3; //некорректный email
        }
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setFirstname(user.getFirstname());
        user.setMiddlename(user.getMiddlename());
        user.setLastname(user.getLastname());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role roleGuest = roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singleton(roleGuest));
        userRepository.save(user);
        return 0;// успешная регистрация
    }

    public boolean deleteUser(Long userId) {
        User usertodelete = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role userrole = roleRepository.findById(2L).orElseThrow(() ->  new RuntimeException("Role not found"));
        if (userRepository.findById(userId).isPresent() && !usertodelete.getRoles().contains(userrole)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public boolean AddRole(Long userId, Long roleId) {
        User usertoedit = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role newRole = roleRepository.findById(roleId).orElseThrow(() ->  new RuntimeException("Role not found"));
        usertoedit.addRole(newRole);
        userRepository.save(usertoedit);
        return true;
    }
    public boolean removeRole(Long userId, Long roleId) {
        User usertoedit = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role newRole = roleRepository.findById(roleId).orElseThrow(() ->  new RuntimeException("Role not found"));
        usertoedit.removeRole(newRole);
        userRepository.save(usertoedit);
        return true;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public Long getRating(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getUserRating();
    }
}
