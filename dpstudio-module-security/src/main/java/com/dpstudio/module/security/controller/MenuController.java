package com.dpstudio.module.security.controller;

import com.dpstudio.dev.security.Security;
import com.dpstudio.dev.security.bean.MenuBean;
import net.ymate.platform.webmvc.annotation.Controller;
import net.ymate.platform.webmvc.annotation.RequestMapping;
import net.ymate.platform.webmvc.util.WebResult;
import net.ymate.platform.webmvc.view.IView;

import java.util.List;

/**
 * @Author: 徐建鹏.
 * @Date: 2020/10/20.
 * @Time: 4:34 下午.
 * @Description: 菜单控制器
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    /**
     * 菜单列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public IView list() throws Exception {
        List<MenuBean> menuBeanList = Security.get().permissionMenu();
        return WebResult.succeed().data(menuBeanList).keepNullValue().toJSON();
    }
}
