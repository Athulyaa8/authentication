package com.rl.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.rl.demo.model.User;
import com.rl.demo.service.UserService;

@Controller
	public class UserController {

	    private final UserService userService;

	    public UserController(UserService userService) {
	        this.userService = userService;
	    }
		
	    @GetMapping("/register")
	    public String showRegistrationForm() {
	    	System.out.print("hloo");
	        return "register";
	    }

	    @PostMapping("/register")
	    public String registerUser(@ModelAttribute User user) {
	        userService.registerUser(user);
	        return "redirect:/login";
	    }

	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "login";
	    }

	    @PostMapping("/login")
	    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
	        User user = userService.loginUser(email, password);
	        if (user != null) {
	            model.addAttribute("user", user);
	            return "w";
	        }
	        model.addAttribute("error", "Invalid email or password");
	        return "login";
	    }
	}


