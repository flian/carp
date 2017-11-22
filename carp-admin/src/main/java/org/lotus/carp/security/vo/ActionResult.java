package org.lotus.carp.security.vo;

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
public class ActionResult implements TreeNode<ActionResult, Integer> {
    private Integer id;
    private Integer parentId;
    private String name;
    private String actionUrl;
    private String actionMethod;
    private Integer priority;
    private boolean leaf;
    private boolean root = false;
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
        root.id = -1;
        root.name = "ROOT";
        root.parentId = 0;
        root.priority = 1;
        root.actionMethod = "ALL";
        root.actionUrl = "";
        root.root = true;
        return root;
    }

    @Override
    public List<ActionResult> getChildren() {
        return children;
    }

    public static ActionResult buildTree(List<ActionResult> items) {
        return (new ActionResult()).build(items, -1);
    }
}
