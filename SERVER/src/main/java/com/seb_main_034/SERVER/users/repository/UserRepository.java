package com.seb_main_034.SERVER.users.repository;

import com.seb_main_034.SERVER.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
