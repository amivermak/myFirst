package com.frugalis.service;

import java.util.List;

import com.frugalis.beans.DTOUser;


public interface UserService {

	public DTOUser saveUser(DTOUser inDTOUser);
	public List<DTOUser> getAllUsers();
	public DTOUser findbyId(Long id);
	public DTOUser updateUser(DTOUser inDTOUser);
	public int deleteUser(Long id);
	public DTOUser incrementCounter(long id);
	
}
