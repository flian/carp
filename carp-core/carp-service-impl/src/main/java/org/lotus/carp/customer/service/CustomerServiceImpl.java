package org.lotus.carp.customer.service;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.lotus.carp.base.utils.CarpBeanUtils;
import org.lotus.carp.customer.converter.CustomerConverter;
import org.lotus.carp.customer.domain.Customer;
import org.lotus.carp.customer.enums.From;
import org.lotus.carp.customer.enums.Gender;
import org.lotus.carp.customer.repository.CustomerRepository;
import org.lotus.carp.customer.vo.CustomerDetailResult;
import org.lotus.carp.customer.vo.CustomerRegisterDto;
import org.lotus.carp.customer.vo.CustomerResult;
import org.lotus.carp.customer.vo.CustomerWechatRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 前端用户服务方法
 *
 * @author: Foy Lian
 * Date: 12/5/2017
 * Time: 4:30 PM
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<CustomerResult> search(String q, Pageable page) {
        return customerRepository.search(q, page).map(customerConverter::convert);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CustomerResult changePassword(String changedByUserId, String targetUserName, String newPassword) {
        Customer target = customerRepository.findByUserName(targetUserName);
        target.setPassword(passwordEncoder.encode(newPassword));
        target.setLastPasswordResetDate(new Date());
        customerRepository.save(target);
        return customerConverter.convert(target);
    }

    /**
     * 用户注册
     *
     * @param dto
     * @return 注册成功用户详情
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CustomerDetailResult register(CustomerRegisterDto dto) {
        Customer customer = new Customer();
        customer.setUuid(UUID.randomUUID().toString());
        customer.setUserName(dto.getUserName());
        customer.setNickName(dto.getNickName());
        customer.setGender(Gender.UNKNOWN);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        customer.setMobile(dto.getMobile());
        customer.setLastPasswordResetDate(new Date());
        customer.setFromChannel(From.NORMAL);
        customer = customerRepository.save(customer);
        return CarpBeanUtils.copy(customer, CustomerDetailResult.class);

    }

    /**
     * 从微信注册或者更新本地账户
     *
     * @param dto
     * @return 注册成功用户详情
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public CustomerDetailResult registerFromWechat(CustomerWechatRegisterDto dto) {
        String uuid = UUID.randomUUID().toString();
        String userName = Strings.isNullOrEmpty(dto.getUnionId()) ? dto.getOpenId() : dto.getUnionId();
        if (Strings.isNullOrEmpty(userName)) {
            userName = uuid;
        }
        //find exist user
        Customer oldCustomer = customerRepository.findByUserName(userName);
        if(null != oldCustomer){
            //should update
            oldCustomer.setNickName(dto.getNickName());
            oldCustomer.setAvatar(dto.getHeadIconUrl());
            oldCustomer.setOpenId(dto.getOpenId());
            oldCustomer.setUnionId(dto.getUnionId());
            return CarpBeanUtils.copy(customerRepository.save(oldCustomer), CustomerDetailResult.class);
        }

        //new user, create account
        Customer customer = new Customer();
        customer.setUuid(UUID.randomUUID().toString());
        customer.setUserName(userName);
        customer.setNickName(dto.getNickName());
        customer.setGender(dto.getGender());
        //random password ensure not one can login.
        customer.setPassword(passwordEncoder.encode(randomPwd()));
        customer.setAvatar(dto.getHeadIconUrl());
        customer.setLastPasswordResetDate(new Date());
        customer.setFromChannel(From.WECHAT);
        customer.setOpenId(dto.getOpenId());
        customer.setUnionId(dto.getUnionId());
        customer.setMobile(Customer.DEFAULT_MOBILE_NO);
        customer = customerRepository.save(customer);
        return CarpBeanUtils.copy(customer, CustomerDetailResult.class);
    }

    /**
     * 获取用户详情
     *
     * @param uuid uuid
     * @return
     */
    @Override
    public CustomerDetailResult getByUuid(String uuid) {
        return CarpBeanUtils.copy(customerRepository.findByUuid(uuid), CustomerDetailResult.class);
    }

    /**
     * 获取用户
     *
     * @param id 用户id
     * @return 用户详情
     */
    @Override
    public CustomerDetailResult userInfo(Long id) {
        return CarpBeanUtils.copy(customerRepository.getOne(id), CustomerDetailResult.class);
    }

    private String randomPwd() {
        byte[] array = new byte[16];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
