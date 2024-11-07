package com.practise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practise.enteties.user;

@Service
@RequestMapping("/api")
public class FirstService {
	
	private static List<user> list = new ArrayList<>();
	
	static {
		
		list.add(new user(1, "Abhay", "Faridabad", "Active"));
		list.add(new user(2, "sher", "Faridabad", "Active"));
		list.add(new user(3, "SHanky", "Faridabad", "Active"));
		
	}
	
	public List<user> getAllUsers()
	{
		
		return list;
		
	}
	
	public user getBook( int id){
		
		user u1 = null;
		u1 = list.stream().filter(e->e.getId()==id).findFirst().get();
		return u1;
		
	}

}
