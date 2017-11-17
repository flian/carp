package org.lotus.carp.profile.service.impl;

import org.lotus.carp.profile.convter.ActionConverter;
import org.lotus.carp.profile.convter.MenuConverter;
import org.lotus.carp.profile.domain.Role;
import org.lotus.carp.profile.repository.ActionRepository;
import org.lotus.carp.profile.repository.MenuRepository;
import org.lotus.carp.profile.repository.RoleRepository;
import org.lotus.carp.profile.vo.ResourceIdListResult;
import org.lotus.carp.profile.vo.ResourceListResult;
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
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private MenuConverter menuConverter;
    @Autowired
    private ActionConverter actionConverter;

    public Page<Role> search(String q, Pageable page) {
        Role queryParams = new Role();
        queryParams.setCode(q);
        queryParams.setName(q);
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Role> query = Example.of(queryParams, matcher);
        return roleRepository.findAll(query, page);
    }

    public List<Role> all() {
        return roleRepository.findAll();
    }

    public ResourceListResult findRoleResources(Long roleId) {
        Role role = roleRepository.findOne(roleId);
        ResourceListResult dto = new ResourceListResult();
        dto.setMenuList(menuConverter.buildTreeWithoutRoot(role.getMenus()));
        dto.setActionList(actionConverter.buildTreeWithoutRoot(role.getActions()));
        return dto;
    }

    public ResourceIdListResult findRoleResourceIds(Long roleId) {
        ResourceIdListResult result = new ResourceIdListResult();
        result.getMenuIdList().addAll(roleRepository.findMenuIdWithRoleId(roleId));
        result.getActionIdList().addAll(roleRepository.findAcitonIdWithRoleId(roleId));
        return result;
    }

    public ResourceListResult allResources() {
        ResourceListResult dto = new ResourceListResult();
        dto.setMenuList(menuConverter.buildTreeWithoutRoot(menuRepository.findAll()));
        dto.setActionList(actionConverter.buildTreeWithoutRoot(actionRepository.findAll()));
        return dto;
    }
}
