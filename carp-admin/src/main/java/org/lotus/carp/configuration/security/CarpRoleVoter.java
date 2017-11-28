package org.lotus.carp.configuration.security;

import org.springframework.security.access.vote.RoleVoter;

/**
 * carp 角色投票器
 *
 * @author: Foy Lian
 * Date: 11/24/2017
 * Time: 10:52 AM
 */
public class CarpRoleVoter extends RoleVoter {
    public CarpRoleVoter() {
        setRolePrefix("");
    }
}
