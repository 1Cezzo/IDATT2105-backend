package com.IDATT2105.IDATT2105.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.IDATT2105.IDATT2105.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  public User findByUsername(String username);

}
