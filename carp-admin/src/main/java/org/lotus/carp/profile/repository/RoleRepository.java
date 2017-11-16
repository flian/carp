package org.lotus.carp.profile.repository;

import org.lotus.carp.profile.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/9/2017
 * Time: 5:22 PM
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
     Role findByCode(String code);

}
