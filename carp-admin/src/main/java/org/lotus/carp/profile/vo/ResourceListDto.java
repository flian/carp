package org.lotus.carp.profile.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu & Action list
 *
 * @author: Foy Lian
 * Date: 11/16/2017
 * Time: 5:18 PM
 */
@Data
public class ResourceListDto {
    List<ActionResult> actionList = new ArrayList<>();
    List<MenuResult> menuList = new ArrayList<>();
}
