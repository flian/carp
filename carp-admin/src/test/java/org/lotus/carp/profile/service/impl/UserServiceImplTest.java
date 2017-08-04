package org.lotus.carp.profile.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lotus.carp.AdminApplication;
import org.lotus.carp.profile.convter.UserConvter;
import org.lotus.carp.profile.domain.User;
import org.lotus.carp.profile.vo.UserResult;
import org.spockframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Foy Lian
 * Date: 8/4/2017
 * Time: 4:48 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminApplication.class)
@DirtiesContext
@Transactional
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserConvter userConvter;

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
                    List<UserResult> list = userConvter.toList(users);
                    Assert.that(list.size() > 0);
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