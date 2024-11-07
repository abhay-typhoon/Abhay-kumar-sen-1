package com.practise.controler; // Corrected the package name spelling

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.enteties.user;
 
import com.practise.service.FirstService; // Corrected the service class name

@RestController
@RequestMapping("/api")
public class FirstControler { 

    @Autowired
    private FirstService firstService; 

   /* @GetMapping("/u1")
    public user getFirst() {
        return this.firstService.getBook(2); 
    }*/

    @GetMapping("/u1")
    public List<user> getAllUsers(@PathVariable Long id ) {
        return this.firstService.getAllUsers(); 
    }
}
