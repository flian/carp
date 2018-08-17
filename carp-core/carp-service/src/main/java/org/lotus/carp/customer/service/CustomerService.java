package org.lotus.carp.customer.service;

import org.lotus.carp.customer.vo.CustomerDetailResult;
import org.lotus.carp.customer.vo.CustomerRegisterDto;
import org.lotus.carp.customer.vo.CustomerResult;
import org.lotus.carp.customer.vo.CustomerWechatRegisterDto;
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

    /**
     * 用户注册
     * @param dto
     * @return 注册成功用户详情
     */
    CustomerDetailResult register(CustomerRegisterDto dto);

    /**
     *  从微信注册或者更新本地账户
     * @param dto
     * @return 注册成功用户详情
     */
    CustomerDetailResult registerFromWechat(CustomerWechatRegisterDto dto);

    /**
     * 获取用户
     * @param id 用户id
     * @return 用户详情
     */
    CustomerDetailResult userInfo(Long id);

    /**
     * 获取用户详情
     * @param uuid uuid
     * @return
     */
    CustomerDetailResult getByUuid(String uuid);

    /**
     * 获取用户详情
     * @param userName
     * @return
     */
    CustomerDetailResult getByUserName(String userName);
}
