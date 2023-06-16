package com.hmy.staybooking.repository;

import com.hmy.staybooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// User - name of the model class
// String - ID type of the model class
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
