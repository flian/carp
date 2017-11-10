package org.lotus.carp.profile.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:25 PM
 */
@Data
public class ActionResult {
    private Integer id;
    private Integer parentId;
    private String name;
    private String actionUrl;
    private String actionMethod;
    private Integer priority;
    private boolean leaf;
}
