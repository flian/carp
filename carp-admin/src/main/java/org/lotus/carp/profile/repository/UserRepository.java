package org.lotus.carp.profile.repository;

import org.lotus.carp.profile.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/3/2017
 * Time: 5:59 PM
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserName(String userName);
}
