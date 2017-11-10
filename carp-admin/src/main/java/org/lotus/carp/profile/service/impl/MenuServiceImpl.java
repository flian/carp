package org.lotus.carp.profile.service.impl;

import org.lotus.carp.base.service.BaseService;
import org.lotus.carp.profile.domain.Menu;
import org.lotus.carp.profile.repository.MenuRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:52 PM
 */
@Service
public class MenuServiceImpl extends BaseService<MenuRepository, Menu, Integer> {

    @Override
    public Example<Menu> createExampleQuery(String q) {
        Menu menuQuery = new Menu();
        menuQuery.setName(q);
        menuQuery.setUrl(q);
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("url", ExampleMatcher.GenericPropertyMatchers.contains());
        return Example.of(menuQuery, matcher);
    }
}
