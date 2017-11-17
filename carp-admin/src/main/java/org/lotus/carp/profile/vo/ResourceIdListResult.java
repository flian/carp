package org.lotus.carp.profile.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 11/17/2017
 * Time: 4:40 PM
 */
@Data
public class ResourceIdListResult {
    private List<Integer> menuIdList=new ArrayList<>();
    private List<Integer> actionIdList=new ArrayList<>();
}
