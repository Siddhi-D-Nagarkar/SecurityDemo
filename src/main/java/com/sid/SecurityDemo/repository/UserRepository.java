package com.sid.SecurityDemo.repository;

import com.sid.SecurityDemo.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByUsername(String username);
}
