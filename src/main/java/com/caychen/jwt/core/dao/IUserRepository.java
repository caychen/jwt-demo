package com.caychen.jwt.core.dao;

import com.caychen.jwt.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2020-03-11
 * @Describe:
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "select id, username, email from tm_user", nativeQuery = true)
    List<User> findUserList();

}
