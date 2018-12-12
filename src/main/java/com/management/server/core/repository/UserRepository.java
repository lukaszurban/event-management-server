package com.management.server.core.repository;

import com.management.server.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Łukasz on 28.11.2018
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select p from person p where p.enabled = true")
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

    @Modifying
    @Query("delete from person p where p.id =:id")
    void deleteUser(@Param("id") Long id);
}
