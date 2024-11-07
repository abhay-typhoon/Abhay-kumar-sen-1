package com.JWT.Conroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JWT.Controller.UserService;
import com.JWT.service.user.User;

@RestController
public class HomeController {

	@Autowired
	private UserService userSerivce;
	
	//http://localhost:8080/home/users
	
	@GetMapping("/users")
	public List<User>getUsers(){
		
		System.out.println("getting Users");
		return this.userSerivce.getUsers();
		
	}
	
}
