package org.lotus.carp.profile.vo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/10/2017
 * Time: 4:24 PM
 */
@Data
public class MenuResult {
    private Integer id;
    private Integer parentId;
    private String name;
    private String url;
    private Integer priority;

}
