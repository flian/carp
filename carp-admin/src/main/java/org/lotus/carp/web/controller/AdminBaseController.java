package org.lotus.carp.web.controller;

import org.lotus.carp.base.web.BaseController;
import org.lotus.carp.utils.ProfileUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * @author : Foy Lian
 * Date: 8/2/2017
 * Time: 5:05 PM
 */
public abstract class AdminBaseController implements BaseController {
    protected HttpServletRequest request() {
        return ProfileUtil.request();
    }
}
