package org.lotus.carp.security.repository;

import org.lotus.carp.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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

    /**
     * 查询给定角色id下的可用菜单
     * @param roleId 角色id
     * @return 菜单
     */
    @Query(value = "SELECT  menu_id FROM carp_role_menu WHERE role_id=:roleId", nativeQuery = true)
    Set<Integer> findMenuIdWithRoleId(@Param("roleId") Long roleId);

    /**
     * 查询给定角色下的可用功能
     * @param roleId 角色id
     * @return 功能
     */
    @Query(value = "SELECT  action_id FROM carp_role_action WHERE role_id=:roleId", nativeQuery = true)
    Set<Integer> findAcitonIdWithRoleId(@Param("roleId") Long roleId);

    /**
     * 查询指定功能actionId 需要的角色code
     * @param actionId 功能id
     * @return 需要的角色
     */
    @Query(value = "SELECT DISTINCT r.role_code FROM carp_role r\n" +
            "LEFT JOIN carp_role_action ra ON ra.role_id = r.id\n" +
            "WHERE ra.action_id=:actionId",nativeQuery = true)
    List<String> listRoleCodeCanAccessActionId(@Param("actionId") Integer actionId);
}
