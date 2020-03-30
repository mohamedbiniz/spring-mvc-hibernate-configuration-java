package com.tpseance4.controllers;

import java.util.List;

import com.tpseance4.entities.User;
import com.tpseance4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		List<User> theUsers = userService.getUsers();
		theModel.addAttribute("users", theUsers);
		return "user/list-users";
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		return "user/user-form";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser) {
		userService.saveUser(theUser);
		return "redirect:/user/list";
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("userId") int theId,
									Model theModel) {
		User theUser = userService.getUser(theId);
		theModel.addAttribute("user", theUser);
		return "user/user-form";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("userId") int theId) {
		userService.deleteUser(theId);
		return "redirect:/user/list";
	}
}
