package com.testlab.user.service.Repo;

import com.testlab.user.service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, String> {
}
