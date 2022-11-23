package org.lotus.carp.customer.repository;

import org.lotus.carp.customer.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:25 PM
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("from Customer c where c.userName LIKE CONCAT('%',:q,'%') or c.nickName LIKE CONCAT('%',:q,'%')")
    Page<Customer> search(@Param("q")String q, Pageable pageable);

    Customer findByUserName(String userName);

    Customer findByUuid(String uuid);
}
