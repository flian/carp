package org.lotus.carp.profile.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:25 PM
 */
@Data
public class ActionResult implements TreeNode<ActionResult,Integer>{
    private Integer id;
    private Integer parentId;
    private String name;
    private String actionUrl;
    private String actionMethod;
    private Integer priority;
    private boolean leaf;
    private boolean deleteAble = true;
    private List<ActionResult> children = new ArrayList<>();
    @Override
    public Integer getKey() {
        return id;
    }

    @Override
    public Integer getParentKey() {
        return parentId;
    }

    @Override
    public ActionResult root() {
        ActionResult root = new ActionResult();
        root.setId(-1);
        root.setName("ROOT");
        root.deleteAble =false;
        return root;
    }
    @Override
    public List<ActionResult> getChildren(){
        return children;
    }
}
