package org.lotus.carp.security.repository;

import org.lotus.carp.security.domain.User;
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
    /**
     * find backend user by user name
     * @param userName
     * @return user
     */
    User findByUserName(String userName);

    /**
     *  query user
     * @param q query key
     * @param pageable page
     * @return users
     */
    @Query("from User u where  u.userName LIKE CONCAT('%',:userName)")
    Page<User> search(@Param("userName") String q, Pageable pageable);
}
