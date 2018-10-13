package com.frugalis.repository;

import com.frugalis.entity.User;

import java.util.List;

public interface UserRepo {

    List<User> findByfirstname(String firstname);
    User findById(Long Id);
}
