package org.lotus.carp.profile.service.impl;

import org.lotus.carp.base.service.BaseService;
import org.lotus.carp.profile.domain.Action;
import org.lotus.carp.profile.repository.ActionRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:52 PM
 */
@Service
public class ActionServiceImpl extends BaseService<ActionRepository, Action, Integer> {

    /**
     * 单表查询
     *
     * @param q 查询关键字
     * @return example
     */
    @Override
    public Example<Action> createExampleQuery(String q) {
        Action action = new Action();
        return Example.of(action);
    }
}
