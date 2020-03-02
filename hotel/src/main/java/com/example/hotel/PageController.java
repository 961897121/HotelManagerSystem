package com.example.hotel;

import com.example.hotel.user.domain.OmUser;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PageController
 * @Description TODO(html页面之间的跳转)
 * @Author
 * @Date 2020/1/5 20:15
 **/
@Controller
public class PageController {
    private static Logger logger = Logger.getLogger(PageController.class);

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login.do")
    public String toLogin(){
        logger.debug("{ 跳转到登录页面 }");
        return "login";
    }

    /**
     * 跳转到管理主页面
     * @return
     */
    @RequestMapping("/index.do")
    public String toIndex(){
        logger.debug("{ 跳转到管理主页面 }");
        return "index";
    }

    /**
     * 跳转到菜单管理页面
     * @return
     */
    @RequestMapping("/menuInfo.do")
    public String toMenu(){
        logger.debug("{ 跳转到菜单管理页面 }");
        return "menu";
    }

    /**
     * 跳转到用户信息管理页面
     * @return
     */
    @RequestMapping("/userInfo.do")
    public String toUser(){
        logger.debug("{ 跳转到用户信息页面 }");
        return "user";
    }

    /**
     * 跳转到厨师管理页面
     * @return
     */
    @RequestMapping("/cookerInfo.do")
    public String cookerInfo(){
        logger.debug("{ 跳转到厨师信息页面 }");
        return "cooker";
    }

    /**
     * 跳转到菜单价格管理页面
     * @return
     */
    @RequestMapping("/menuTypeInfo.do")
    public String menuTypeInfo(){
        logger.debug("{ 跳转到菜单价格管理页面 }");
        return "menuType";
    }
    /**
     * 获取用户信息的方法
     * @param request
     * @return
     */
    @RequestMapping("/getCurrentUser.do")
    @ResponseBody
    public Object getCurrentUser(HttpServletRequest request){
        logger.debug("{ 获取个人信息 }");
        OmUser omUser =(OmUser) request.getSession().getAttribute("user");
        return omUser;
    }

    /**
     * 跳转到订单管理页面
     * @return
     */
    @RequestMapping("/order.do")
    public String toOrder(){
        logger.debug("{ 跳转到订单管理页面 }");
        return "order";
    }
}
