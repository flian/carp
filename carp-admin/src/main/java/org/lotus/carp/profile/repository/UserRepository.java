package org.lotus.carp.profile.repository;

import org.lotus.carp.profile.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



/**
 * Created with IntelliJ IDEA.
 * @author : Foy Lian
 * Date: 8/3/2017
 * Time: 5:59 PM
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    @Query("from User u where  u.userName LIKE CONCAT('%',:userName)")
    Page<User> search(@Param("userName") String q, Pageable pageable);
}
