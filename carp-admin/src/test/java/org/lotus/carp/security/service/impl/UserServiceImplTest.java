package org.lotus.carp.security.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lotus.carp.AdminApplication;
import org.lotus.carp.security.convter.UserConverter;
import org.lotus.carp.security.domain.User;
import org.lotus.carp.security.vo.UserResult;
import org.spockframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 *  集成测试示例。 测试userService方法。也可改造后测试API
 * @author : Foy Lian
 * Date: 8/4/2017
 * Time: 4:48 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminApplication.class)
@DirtiesContext
@Transactional
@ActiveProfiles("it")
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserConverter userConvter;

    private String[] userExist = {"admin", "user"};
    ;
    private String[] userNotExist = {"adminNoExist", "userNotExist"};

    @Test
    public void testLoadUserByUsername() {
        Arrays.stream(userExist).forEach(userName -> {
            UserDetails userDetail = userService.loadUserByUsername(userName);
            Assert.that(userDetail.getUsername().equals(userName));
            Assert.that(userDetail.getAuthorities().size() > 0);
        });
        Arrays.stream(userNotExist).forEach(userName -> {
            try {
                userService.loadUserByUsername(userName);
                Assert.fail("Excepted an NullPointerException to be thrown.");
            } catch (NullPointerException ne) {

            } catch (Exception e) {
                Assert.fail("Excepted an NullPointerException to be thrown.");
            }
        });
    }

    @Test
    public void testSearch() throws Exception {

        Arrays.stream(userExist).forEach(
                q -> {
                    Page<User> users = userService.search(q, null);
                    Assert.that(users.getTotalElements() > 0);
                    users.forEach(user -> {
                        Assert.that(user.getUserName().startsWith(q));
                        UserResult result = userConvter.toResult(user);
                        Assert.that(result.getUserName().startsWith(q));
                    });
                    Page<UserResult> list = users.map(userConvter::convert);
                    Assert.that(list.getTotalElements() > 0);
                }
        );

        Arrays.stream(userNotExist).forEach(
                q -> {
                    Page<User> users = userService.search(q, null);
                    Assert.that(users.getTotalElements() == 0);
                }
        );
    }

}