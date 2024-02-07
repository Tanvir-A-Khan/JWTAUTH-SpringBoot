package com.example.SpringWebSecurity6JWT;

import com.example.SpringWebSecurity6JWT.models.Role;
import com.example.SpringWebSecurity6JWT.models.User;
import com.example.SpringWebSecurity6JWT.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringWebSecurity6JwtApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringWebSecurity6JwtApplication.class, args);
	}


	public void run(String... args){
		User admin = userRepository.findByRole(Role.ADMIN);
		if(admin == null){
			User user = new User();
			user.setEmail("admin@rokomari.com");
			user.setName("ADMIN");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);
			user.setPhone("015555555555");
			userRepository.save(user);
		}
	}

}
