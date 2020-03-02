package com.example.hotel.user.controller;

import com.example.hotel.user.domain.OmUser;
import com.example.hotel.user.service.UserService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author
 * @Date 2020/1/5 22:31
 **/

@RestController
@RequestMapping("/user")
@Api(value = "用户controller" , tags = "用户操作接口")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @param omUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info.do" , method = RequestMethod.POST)
    @ResponseBody
    public Object getInfo(@RequestBody OmUser omUser) throws Exception{
        logger.debug("{ 用户获取个人信息 }");
        List<OmUser> omUserList = userService.selectList(omUser);
        return omUserList.get(0);
    }

    /**
     * 验证用户登录
     * @param omUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login.do" , method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkLogin(@RequestBody OmUser omUser , HttpServletRequest request) throws Exception{
        logger.debug("{ 有用户验证登录 }");
        boolean flag = false;
        flag = userService.checkLogin(omUser , request);
        return flag;
    }

    /**
     * 验证管理员登录
     * @param omUser
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/manageLogin.do" , method = RequestMethod.POST)
    @ResponseBody
    public Boolean managerLogin(@RequestBody OmUser omUser , HttpServletRequest request) throws Exception{
        logger.debug("{ 管理员验证登录 }");
        boolean flag = false;
        flag = userService.managerLogin(omUser , request);
        return flag;
    }

    /**
     * 添加/注册用户
     * @param omUser
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/save.do")
    public Boolean save(@RequestBody OmUser omUser) throws Exception{
        logger.debug("{ 新增用户 }");
        boolean flag = userService.save(omUser);
        return flag;
    }

    /**
     * 更新用户信息
     * @param omUser
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/update.do")
    public Boolean update(@RequestBody OmUser omUser) throws Exception{
        logger.debug("***用户更改信息***");
        boolean flag = userService.update(omUser);
        return flag;
    }

    /**
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout.do")
    public Boolean logout(HttpServletRequest request) throws Exception{
        logger.debug("{ 有用户退出 }");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return true;
    }
}
