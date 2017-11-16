package org.lotus.carp.profile.service.impl;

import org.lotus.carp.profile.domain.Role;
import org.lotus.carp.profile.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/9/2017
 * Time: 5:24 PM
 */
@Service
public class RoleServiceImpl {
    @Autowired
    private RoleRepository roleRepository;

    public Page<Role> search(String q, Pageable page) {
        Role queryParams = new Role();
        queryParams.setCode(q);
        queryParams.setName(q);
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("code",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name",ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Role> query = Example.of(queryParams, matcher);
        return roleRepository.findAll(query, page);
    }

    public List<Role> all(){
        return roleRepository.findAll();
    }
}
