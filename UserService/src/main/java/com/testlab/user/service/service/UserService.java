package com.testlab.user.service.service;

import com.testlab.user.service.Repo.UserRepo;
import com.testlab.user.service.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    //user operations

    //create
    Users saveUser(Users user);

    //get all user
    List<Users> getAllUser();

    //get single user of given userId
    Users getUser(String userId);

    //Update

    //Delete

}
