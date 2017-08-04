package org.lotus.carp;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/4/2017
 * Time: 11:21 AM
 */

public class PasswordHashTest {
    @Test
    public void BCryptPasswordEncoderTest(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawAdminPassword = "admin123";
        String rawUserPassword = "user123";
        String hashAdminPassword = passwordEncoder.encode(rawAdminPassword);
        String hashUserPassword = passwordEncoder.encode(rawUserPassword);
        System.out.println("admin, password:"+hashAdminPassword);
        System.out.println("user, password:"+passwordEncoder.encode(hashUserPassword));
        Assert.assertTrue("admin password should match.",passwordEncoder.matches(rawAdminPassword,hashAdminPassword));
        Assert.assertTrue("admin password should match.",passwordEncoder.matches(rawUserPassword,hashUserPassword));
    }
}
