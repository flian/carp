package org.lotus.carp.commerce.customer.service;

import org.lotus.carp.commerce.customer.converter.CustomerConverter;
import org.lotus.carp.commerce.customer.repository.CustomerRepository;
import org.lotus.carp.commerce.customer.vo.CustomerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:30 PM
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public Page<CustomerResult> search(String q, Pageable page) {
        return customerRepository.search(q, page).map(customerConverter);
    }
}
