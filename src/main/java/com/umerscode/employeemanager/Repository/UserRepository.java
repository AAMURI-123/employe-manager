package com.umerscode.employeemanager.Repository;

import com.umerscode.employeemanager.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUsername(String username);
}
