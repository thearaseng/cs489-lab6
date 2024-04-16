package com.theara.lab6;

import com.theara.lab6.model.Role;
import com.theara.lab6.model.User;
import com.theara.lab6.repository.RoleRepository;
import com.theara.lab6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SampleDataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run() {
        // Create roles
        Role userRole = new Role(null, "USER", null);
        Role adminRole = new Role(null, "ADMIN", null);
//        roleRepository.save(userRole);
//        roleRepository.save(adminRole);

        // Create users
        User user = new User(null, "user1", passwordEncoder.encode("123"), "John", "Doe", "user1@example.com",
                true, true, true, true);
        user.setRoles(List.of(userRole));
        userRepository.save(user);

        User admin = new User(null, "admin1", passwordEncoder.encode("123"), "Admin", "User", "admin1@example.com",
                true, true, true, true);
        admin.setRoles(List.of(adminRole));
        userRepository.save(admin);
    }

}
