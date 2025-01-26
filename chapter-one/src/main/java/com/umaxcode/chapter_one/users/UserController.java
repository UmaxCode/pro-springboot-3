package com.umaxcode.chapter_one.users;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<String, User> users = new HashMap<>() {
        {
            put("first@gmail.com", new User("first@gmail.com", "first"));
            put("second@gmail.com", new User("second@gmail.com", "second"));
        }
    };

    @GetMapping
    public Collection<User> getUsers() {
        return this.users.values();
    }

    @GetMapping("/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return this.users.get(email);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        this.users.put(user.getEmail(), user);
        return user;
    }

    @DeleteMapping("/{email}")
    public void deleteByEmail(@PathVariable String email) {
        this.users.remove(email);
    }
}

