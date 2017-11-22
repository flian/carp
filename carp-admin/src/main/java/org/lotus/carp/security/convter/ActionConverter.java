package org.lotus.carp.security.convter;

import org.lotus.carp.security.domain.Action;
import org.lotus.carp.security.vo.ActionResult;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:30 PM
 */
@Component
public class ActionConverter implements Converter<Action, ActionResult> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ActionResult convert(Action source) {
        ActionResult result = new ActionResult();
        BeanUtils.copyProperties(source, result);
        return result;
    }

    public List<ActionResult> map(Iterable<Action> list) {
        List<ActionResult> result = new ArrayList<>();
        list.forEach(item -> result.add(convert(item)));
        return result;
    }

    public List<ActionResult> buildTree(Iterable<Action> list) {
        List<ActionResult> result = new ArrayList<>();
        result.add(ActionResult.buildTree(map(list)));
        return result;
    }

    public List<ActionResult> buildTreeWithoutRoot(Iterable<Action> list) {
        return ActionResult.buildTree(map(list)).getChildren();
    }
}
