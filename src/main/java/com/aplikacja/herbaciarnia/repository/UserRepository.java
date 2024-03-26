package com.aplikacja.herbaciarnia.repository;

import com.aplikacja.herbaciarnia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
