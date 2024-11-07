package com.practise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.practise.dao.userRepository;
import com.practise.enteties.user;

@SpringBootApplication
public class Firststs1Application {

	public static void main(String[] args) {
		
		 ConfigurableApplicationContext context =  SpringApplication.run(Firststs1Application.class, args);
	     
		 userRepository userRepository1 = context.getBean(userRepository.class);

		 user user1 = new user();
		 user1.setName("Abhay Kumar sen");
		 user1.setCity("Haryana");
		 user1.setStatus("I am a programmer");
		 
		 user user2 = userRepository1.save(user1);
		 
		 System.out.println(user1);
	}
	

}
