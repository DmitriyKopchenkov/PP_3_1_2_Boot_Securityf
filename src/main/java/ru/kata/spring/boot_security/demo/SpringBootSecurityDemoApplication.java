package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		ApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		try {
			UserService userService = context.getBean(UserService.class);
			RoleService roleService = context.getBean(RoleService.class);

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

			User user1 = new User("admin", "admin", "admin@mail.ru"
					, bCryptPasswordEncoder.encode("admin"));
			User user2 = new User("user", "user", "user@mail.ru"
					, bCryptPasswordEncoder.encode("user"));
			User user3 = new User("ivan", "ivan", "ivan@mail.ru"
					, bCryptPasswordEncoder.encode("ivan"));

			Role roleAdmin = new Role("ROLE_ADMIN");
			Role roleUser = new Role("ROLE_USER");

			List<Role> rolesAdmUs = new ArrayList<>();
			rolesAdmUs.add(roleAdmin);
			rolesAdmUs.add(roleUser);

			List<Role> rolesUs = new ArrayList<>();
			rolesUs.add(roleUser);

			user1.setRoles(rolesAdmUs);
			user2.setRoles(rolesUs);
			user3.setRoles(rolesUs);

			roleService.add(roleAdmin);
			roleService.add(roleUser);

			userService.addUser(user1);
			userService.addUser(user2);
			userService.addUser(user3);

		} catch (Exception ignored) {
		}
	}

}
