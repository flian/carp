package org.lotus.carp.security.repository;

import org.lotus.carp.security.domain.UserLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 登录日志
 *
 * @author: Foy Lian
 * Date: 5/16/2019
 * Time: 8:23 AM
 */
public interface UserLoginLogRepository extends JpaRepository<UserLoginLog, Long> {
}
