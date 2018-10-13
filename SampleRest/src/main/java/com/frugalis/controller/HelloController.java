package com.frugalis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frugalis.beans.DTOUser;
import com.google.gson.Gson;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.frugalis.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class HelloController {

	@Autowired
	UserService userService;


//	@GetMapping("/test/{name}")
//	public DTOUser testUser(@PathVariable("name")String name, @RequestParam("id") String id)
//	{
//		System.out.println(name);
//		System.out.println(id);
//		DTOUser user = new DTOUser();
//		user.setId(1L);
//		user.setFirstname("Anurag");
//		user.setLastname("Pundir");
//		user.setInstitute("exponent");
//		return user;
//	}
//
//	@PostMapping(value = "/post",consumes = "application/json", produces = "application/json")
//	public ResponseEntity<DTOUser> postData(HttpServletRequest request, @RequestBody DTOUser user)
//	{
//		return new ResponseEntity<DTOUser>(user,HttpStatus.ACCEPTED);
//	}

	
	@PostMapping("/users")
	public DTOUser save(@RequestBody DTOUser u, HttpServletResponse response) {

		return userService.saveUser(u);
	}


	@GetMapping("/saveUser")
	public DTOUser saveUser() {
		DTOUser user = new DTOUser();
		user.setFirstname("Anurag");
		user.setLastname("Pundir");
		user.setInstitute("exponent");
		user.setCounter(1);
		return userService.saveUser(user);
	}

	@GetMapping("/users")
	public ResponseEntity<Object> getAll() {
		List<DTOUser> dTOUsers;
		try {
		 dTOUsers = userService.getAllUsers();
		if(dTOUsers.isEmpty())
		{
			return new ResponseEntity<Object>("No DTOUsers",HttpStatus.BAD_REQUEST);
		}
	}catch (Exception e)
	{
		return new ResponseEntity<Object>("THere was some error",HttpStatus.INTERNAL_SERVER_ERROR);
	}
		return  ResponseEntity.ok().body(dTOUsers);
 
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<DTOUser> getUserById(@PathVariable(value = "id") Long userId) {
	    DTOUser DTOUser = userService.findbyId(userId);
	    if(DTOUser == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(DTOUser);
	}

	@GetMapping("/increment/{id}")
	public ResponseEntity<DTOUser> incrementCounter(@PathVariable(value = "id") Long userId) {
		DTOUser DTOUser = userService.incrementCounter(userId);
		if(DTOUser == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(DTOUser);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long userId,@RequestBody DTOUser inDTOUser) {
		inDTOUser.setId(userId);
		DTOUser DTOUser = userService.updateUser(inDTOUser);
	    if(DTOUser == null) {
	        return ResponseEntity.ok().body(new String("Not Found"));
	    }
	    return ResponseEntity.ok().body(DTOUser);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long userId) {
		
		int  user = userService.deleteUser(userId);
	    if(user<=0) {
	        return ResponseEntity.ok().body(new String("Not Found"));
	    }
	    return ResponseEntity.ok().body(new String("Deleted SuccessFully"));
	}


	public static void main(String[] args) {
		DTOUser user = new DTOUser();
		user.setId(1L);
		user.setFirstname("Anurag");
		user.setLastname("Pundir");
		user.setInstitute("exponent");
	Gson gson = new Gson();
		System.out.println(gson.toJson(user));

		String userJson = "{\"id\":1,\"firstname\":\"Anurag\",\"lastname\":\"Pundir\",\"institute\":\"exponent\"}";
		DTOUser user1 = gson.fromJson(userJson,DTOUser.class);
		System.out.println(user1.getLastname());
	}
	
}
