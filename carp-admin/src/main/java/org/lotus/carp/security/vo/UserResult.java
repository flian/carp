package org.lotus.carp.security.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author : Foy Lian
 * Date: 8/4/2017
 * Time: 3:54 PM
 */
@Data
public class UserResult {
    private Long id;
    private String userName;
    private String roles;
    private List<String> roleCodes = new ArrayList<>();
}
