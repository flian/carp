package org.lotus.carp.security.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:24 PM
 */
@Data
public class MenuResult implements TreeNode<MenuResult, Integer> {
    private Integer id;
    private Integer parentId;
    private String name;
    private String url;
    private Integer priority;
    private boolean leaf;
    private boolean root = false;

    private List<MenuResult> children = new ArrayList<>();

    @Override
    public Integer getKey() {
        return id;
    }

    @Override
    public Integer getParentKey() {
        return parentId;
    }
    @Override
    public MenuResult root() {
        MenuResult root = new MenuResult();
        root.setId(-1);
        root.setParentId(0);
        root.setPriority(1);
        root.setUrl("");
        root.setName("ROOT");
        root.root = true;
        return root;
    }
    @Override
    public List<MenuResult> getChildren(){
        return children;
    }

    public static MenuResult buildTree(List<MenuResult> items){
        return (new MenuResult()).build(items,-1);
    }
}
