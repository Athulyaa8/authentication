package com.rl.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rl.demo.model.User;
import com.rl.demo.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	// Inject PasswordEncoder using constructor
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Register User with encoded password
	public void registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); // ✅ Hashing only once
		userRepository.save(user);
	}

	// Login Validation
	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);

		// Debugging logs
		System.out.println("Stored Password in DB: " + user.getPassword());
		System.out.println("Entered Password: " + password);
		System.out.println("Password Match: " + passwordEncoder.matches(password, user.getPassword()));

		if (user != null && passwordEncoder.matches(password, user.getPassword())) {
			return user; // ✅ Password matches, return user
		}

		return null; // ❌ Incorrect password, return null
	}
}
