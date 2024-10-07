package com.example.SpringDTORestProject.repository;

import com.example.SpringDTORestProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.mobile_no = :no")
    public User findByMobile(@Param("no") String mobileNo);

}
