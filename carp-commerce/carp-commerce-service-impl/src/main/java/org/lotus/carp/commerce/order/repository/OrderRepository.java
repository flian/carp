package org.lotus.carp.commerce.order.repository;

import org.lotus.carp.commerce.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:39 PM
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
}
