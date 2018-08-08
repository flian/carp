package org.lotus.carp.security.service.impl;

import com.google.common.base.Preconditions;
import org.lotus.carp.base.service.BaseService;
import org.lotus.carp.security.domain.Action;
import org.lotus.carp.security.repository.ActionRepository;
import org.lotus.carp.security.vo.ActionCreateDto;
import org.lotus.carp.security.vo.ActionUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 3:52 PM
 */
@Service
public class ActionServiceImpl extends BaseService<ActionRepository, Action, Integer, ActionCreateDto, ActionUpdateDto> {
    @Autowired
    private ActionRepository actionRepository;

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

    @Override
    public Action newOne() {
        return new Action();
    }

    @Transactional(rollbackFor = {Exception.class})
    public Action deleteActionById(Integer id) {
        int count = actionRepository.actionAssignedRolesCount(id);
        Preconditions.checkArgument(0 == count, String.format("功能已经授予%d个角色，请取消授权后在删除菜单!", count));
        return delete(id);
    }

    @Override
    public Integer getUpdateDtoId(ActionUpdateDto updateDto) {
        return updateDto.getId();
    }
}
