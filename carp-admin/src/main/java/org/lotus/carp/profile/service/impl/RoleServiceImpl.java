package org.lotus.carp.profile.service.impl;

import com.google.common.base.Preconditions;
import org.lotus.carp.profile.convter.ActionConverter;
import org.lotus.carp.profile.convter.MenuConverter;
import org.lotus.carp.profile.domain.Action;
import org.lotus.carp.profile.domain.Menu;
import org.lotus.carp.profile.domain.Role;
import org.lotus.carp.profile.repository.ActionRepository;
import org.lotus.carp.profile.repository.MenuRepository;
import org.lotus.carp.profile.repository.RoleRepository;
import org.lotus.carp.profile.vo.ResourceIdListResult;
import org.lotus.carp.profile.vo.ResourceListResult;
import org.lotus.carp.profile.vo.RoleCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Transactional(rollbackFor = {Exception.class})
    public Role updateRoleMenu(Long roleId, List<Integer> menuIds) {
        Role role = roleRepository.findOne(roleId);
        Set<Menu> newMenus = new HashSet<>();
        menuIds.forEach(menuId -> newMenus.add(menuRepository.getOne(menuId)));
        role.setMenus(newMenus);
        return roleRepository.save(role);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Role updateRoleAction(Long roleId, List<Integer> actionIds) {
        Role role = roleRepository.getOne(roleId);
        Set<Action> newAcitons = new HashSet<>();
        actionIds.forEach(actionId -> newAcitons.add(actionRepository.getOne(actionId)));
        role.setActions(newAcitons);
        return roleRepository.save(role);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Role createRole(RoleCreateDto dto) {
        Preconditions.checkArgument(roleRepository.findByCode(dto.getCode()) == null, "角色编码已存在!");
        Role role = new Role();
        role.setCode(dto.getCode());
        role.setName(dto.getName());
        return roleRepository.save(role);
    }
}
