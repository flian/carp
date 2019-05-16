package org.lotus.carp.security.service.impl;

import com.google.common.base.Preconditions;
import org.lotus.carp.base.event.SecurityResourceChangedEvent;
import org.lotus.carp.security.domain.Role;
import org.lotus.carp.security.domain.User;
import org.lotus.carp.security.domain.UserLoginLog;
import org.lotus.carp.security.repository.RoleRepository;
import org.lotus.carp.security.repository.UserLoginLogRepository;
import org.lotus.carp.security.repository.UserRepository;
import org.lotus.carp.security.vo.UserCreateDto;
import org.lotus.carp.security.vo.UserPasswordDto;
import org.lotus.carp.security.vo.UserRoleUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统用户服务
 *
 * @author : Foy Lian
 * Date: 8/3/2017
 * Time: 6:02 PM
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserLoginLogRepository loginLogRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected ApplicationEventPublisher publisher;

    @Override
    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        Preconditions.checkNotNull(user, "No user present with userName: " + userName);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }

    @Transactional(readOnly = true, rollbackFor = {Exception.class})
    public Page<User> search(String q, Pageable page) {
        return userRepository.search(q, page);
    }

    @Transactional(rollbackFor = {Exception.class})
    public User updateUserRole(UserRoleUpdateDto userRoleUpdateDto) {
        User user = userRepository.getOne(userRoleUpdateDto.getUserId());
        Set<Role> roles = new HashSet<>();
        user.setRoles(roles);
        userRoleUpdateDto.getRoles().forEach(code -> roles.add(roleRepository.findByCode(code)));
        User result = userRepository.save(user);
        publisher.publishEvent(new SecurityResourceChangedEvent());
        return result;
    }

    @Transactional(rollbackFor = {Exception.class})
    public User createUser(UserCreateDto userCreateDto) {
        Preconditions.checkArgument(userRepository.findByUserName(userCreateDto.getName()) == null, "待创建用户已存在!");
        User newUser = new User();
        newUser.setUserName(userCreateDto.getName());
        newUser.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        User result = userRepository.save(newUser);
        publisher.publishEvent(new SecurityResourceChangedEvent());
        return result;
    }

    @Transactional(rollbackFor = {Exception.class})
    public User updatePassword(String userName, UserPasswordDto userPasswordDto) {
        User user = userRepository.findByUserName(userName);
        Preconditions.checkArgument(user != null, "找不到待改密的用户!");
        user.setPassword(passwordEncoder.encode(userPasswordDto.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    public UserLoginLog saveLog(String userName, String agent, String ip, String log) {
        UserLoginLog logEntity = new UserLoginLog();
        logEntity.setAgent(agent);
        logEntity.setUserName(userName);
        logEntity.setIp(ip);
        logEntity.setLog(log);
        logEntity.setCreatedDatetime(new Date());
        return loginLogRepository.save(logEntity);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Boolean updateUserEnableStatus(String userName, Boolean enable) {
        Preconditions.checkNotNull(enable, "用户启用状态不能为空!");
        User user = userRepository.findByUserName(userName);
        if (null != user) {
            user.setEnable(enable);
            userRepository.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Transactional(rollbackFor = {Exception.class})
    public void disableUser(String userName) {
        User user = userRepository.findByUserName(userName);
        if (null != user) {
            user.setEnable(Boolean.FALSE);
            userRepository.save(user);
        }
    }

    public Boolean canLogin(String userName) {
        User user = userRepository.findByUserName(userName);
        if (null != user) {
            return user.getEnable();
        }
        return Boolean.FALSE;
    }
}
