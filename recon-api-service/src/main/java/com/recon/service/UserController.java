// File: UserController.java

package com.recon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public UserController() {
        users.addAll(Arrays.asList("Alice", "Bob", "Charlie"));
    }
    private final List<String> users = new ArrayList<>();

    // GET: /api/users
    @GetMapping
    public List<User> getUsers() {

        List<User> users = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new User(
                        i,
                        "User" + i,
                        i % 2 == 0 ? "user" + i + "@example.com" : null, // email is optional
                        i % 3 == 0 ? 20 + i : null                        // age is optional
                ))
                .collect(Collectors.toList());

        users.forEach(System.out::println);
        return users;
    }

    // POST: /api/users
    @PostMapping
    public String createUser(@RequestBody Map<String, String> payload) {
        String name = payload.get("name");
        users.add(name);
        return "User '" + name + "' created successfully.";
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/db")
    public List<UserEntity> get2Users() {
        return userRepository.findAll();
    }

//    @PostMapping
//    public User addUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
}
