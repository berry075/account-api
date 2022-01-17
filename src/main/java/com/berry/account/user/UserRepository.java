package com.berry.account.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByTel(String tel);

    @Transactional
    @Modifying
    @Query("UPDATE User u "
        + "SET "
        + "u.password = :newPassword, "
        + "u.lastUpdateAt = now() "
        + "WHERE u.id = :id AND u.password = :password")
    int modifyPassword(@Param("id") Long id, @Param("password") String password,
        @Param("newPassword") String newPassword);
}
