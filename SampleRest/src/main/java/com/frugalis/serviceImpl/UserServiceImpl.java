package com.frugalis.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.frugalis.beans.DTOUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frugalis.repository.UserRepository;
import com.frugalis.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;


	@Override
	public DTOUser saveUser(DTOUser inDTOUser) {

		com.frugalis.entity.User outUser2 = new com.frugalis.entity.User();

		BeanUtils.copyProperties(inDTOUser, outUser2);

		outUser2 = userRepository.save(outUser2);

		BeanUtils.copyProperties(outUser2, inDTOUser);
		return inDTOUser;
	}

	@Override
	public List<DTOUser> getAllUsers() {

		List<com.frugalis.entity.User>alluser=userRepository.findAll();
		List<DTOUser> outList=new ArrayList<DTOUser>();
		for (com.frugalis.entity.User user : alluser) {
			DTOUser usr=new DTOUser();
			BeanUtils.copyProperties(user, usr);
			outList.add(usr);
			
		}

		return outList;
	}

	@Override
	public DTOUser findbyId(Long id) {
		// TODO Auto-generated method stub
	com.frugalis.entity.User dbUser=userRepository.findOne(id);
	DTOUser outDTOUser =null;
	if(dbUser!=null){
		outDTOUser =new DTOUser();
		BeanUtils.copyProperties(dbUser, outDTOUser);
	}
	
	
		return outDTOUser;
	}

	@Override
	public DTOUser updateUser(DTOUser inDTOUser) {
		// TODO Auto-generated method stub
		com.frugalis.entity.User dbUser=userRepository.findById(inDTOUser.getId());
		if(dbUser!=null){dbUser.setFirstname(inDTOUser.getFirstname());
		dbUser.setInstitute(inDTOUser.getInstitute());
		dbUser.setLastname(inDTOUser.getLastname());
		userRepository.save(dbUser);
		BeanUtils.copyProperties(dbUser, inDTOUser);
		return inDTOUser;

		}else {
			return null;
		}
	}

	@Override
	public DTOUser incrementCounter(long id) {
		// TODO Auto-generated method stub
		com.frugalis.entity.User dbUser=userRepository.findOne(id);
		if(dbUser!=null){
			dbUser.setCounter(dbUser.getCounter()+1);
			userRepository.save(dbUser);
			DTOUser dtoUser = new DTOUser();
			BeanUtils.copyProperties(dbUser, dtoUser);
			return dtoUser;

		}else {
			return null;
		}
	}

	@Override
	public int deleteUser(Long id) {
	
	try{
		com.frugalis.entity.User dbUser=userRepository.findById(id);
		if(dbUser==null){return -1;}
		else {
			userRepository.delete(id);
			return 1;
		}
		
	}catch(Exception e){
		
	}
	return 0;
		
	
	}



}
