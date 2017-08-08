package org.lotus.carp;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/4/2017
 * Time: 11:21 AM
 */

public class PasswordHashTest {
    @Test
    public void bcryptPasswordEncoderTest(){
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
    @Test
    public void emailAddrRegEest(){
        Pattern VALID_EMAIL_ADDRESS_REGEX =Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        String validEmailStr ="Juan.Liang@aaa-int.com;juansdhedng.lian@pxadctdera.com;ljs2213420403@gmail.com;1272974445@qq.com;123@qq.com";
        Arrays.stream(validEmailStr.split(";")).forEach(e ->{
            Assert.assertTrue(VALID_EMAIL_ADDRESS_REGEX.matcher(e).find());
        });
        String invalidEmailStr=";aa@aa.c;";
        Arrays.stream(invalidEmailStr.split(";")).forEach(e ->{
            Assert.assertFalse(VALID_EMAIL_ADDRESS_REGEX.matcher(e).find());
        });
    }
}
