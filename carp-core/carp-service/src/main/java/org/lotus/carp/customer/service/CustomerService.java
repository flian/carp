package org.lotus.carp.customer.service;

import org.lotus.carp.customer.vo.CustomerResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:28 PM
 */
public interface CustomerService {
    Page<CustomerResult> search(String q, Pageable page);

    CustomerResult changePassword(String changedByUserId, String targetUserName, String newPassword);
}
