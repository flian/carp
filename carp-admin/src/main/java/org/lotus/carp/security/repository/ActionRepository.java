package org.lotus.carp.security.repository;

import org.lotus.carp.security.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:51 PM
 */
public interface ActionRepository extends JpaRepository<Action, Integer> {
    @Query(value = "FROM Action a where a.leaf=:isLeaf")
    List<Action> listByLeaf(@Param("isLeaf") boolean isLeaf);

    default List<Action> listByLeaf() {
        return listByLeaf(true);
    }
}
