package org.lotus.carp.security.repository;

import org.lotus.carp.security.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * menu 菜单repository
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:50 PM
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    /**
     * 计算给定的menu，被授予了多少角色
     * @param menuId
     * @return
     */
    @Query(value = "SELECT COUNT(DISTINCT menu_id ) FROM  carp_role_menu where menu_id =:menuId"
            ,nativeQuery = true)
    int menuAssignedRolesCount(@Param("menuId") Integer menuId);
}
