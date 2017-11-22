package org.lotus.carp.security.repository;

import org.lotus.carp.security.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:51 PM
 */
public interface ActionRepository extends JpaRepository<Action, Integer> {
}
