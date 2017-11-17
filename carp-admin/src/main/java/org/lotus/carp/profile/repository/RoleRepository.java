package org.lotus.carp.profile.repository;

import org.lotus.carp.profile.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/9/2017
 * Time: 5:22 PM
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByCode(String code);

    @Query(value = "SELECT  menu_id FROM carp_role_menu WHERE role_id=:roleId", nativeQuery = true)
    Set<Integer> findMenuIdWithRoleId(@Param("roleId") Long roleId);

    @Query(value = "SELECT  action_id FROM carp_role_action WHERE role_id=:roleId", nativeQuery = true)
    Set<Integer> findAcitonIdWithRoleId(@Param("roleId") Long roleId);
}
