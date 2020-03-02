package com.example.hotel.user.service.impl;

import com.example.hotel.user.domain.OmUser;
import com.example.hotel.user.mapper.OmUserMapper;
import com.example.hotel.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/1/20 15:39
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private OmUserMapper omUserMapper;

    @Override
    public OmUser selectOneById(String userId) throws Exception {
        OmUser omUser = omUserMapper.selectOneById(userId);

        if (omUser != null){
            return omUser;
        }
        return null;
    }

    @Override
    public List<OmUser> selectList(OmUser omUser) throws Exception {
        List<OmUser> userList = omUserMapper.selectList(omUser);
        return userList;
    }

    @Override
    public boolean checkLogin(OmUser omUser , HttpServletRequest request) throws Exception {
        //1.查找符合条件的用户列表
        OmUser user = new OmUser();
        user.setLoginName(omUser.getLoginName());
        List<OmUser> users = this.selectList(user);

        //2.判断数据库中该登录名跟密码是否跟用户输入的一致并且判断是否启用该用户
        if (users.size() > 0) {
            for (OmUser dbUser: users) {
                if (dbUser.getLoginPwd().equals(omUser.getLoginPwd()) && dbUser.getUserLock().equals("启用")){
                    //3.设置session储存用户信息
                    HttpSession session = request.getSession();
                    session.setAttribute("user" , dbUser);
                    session.setMaxInactiveInterval(300000000);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean save(OmUser omUser) throws Exception {
        boolean flag = false;
        OmUser user = new OmUser();
        user.setLoginName(omUser.getLoginName());

        List<OmUser> userList = omUserMapper.selectList(user);
        if (userList.size() > 0){
            flag = false;
        } else{
            //若注册时用户名为空，默认用户名为智慧食客
            if (StringUtils.isEmpty(omUser.getUserName())){
                omUser.setUserName("智慧食客");
                omUser.setUserLock("启用");
                omUser.setIdentity("食客");
            }
            //设置创建时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = simpleDateFormat.format(date);

            omUser.setCreateTime(time);
            omUserMapper.insertOne(omUser);
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean update(OmUser omUser) throws Exception {
        boolean flag = false;

        if (omUser.getId() != null && !omUser.getId().equals("")){
            omUserMapper.updateOne(omUser);
            flag = true;
        }
        return flag;
    }

    @Override
    public void updateOne(OmUser omUser) throws Exception {
        omUserMapper.updateOne(omUser);
    }

    @Override
    public void deleteOneById(String id) throws Exception {

    }

    @Override
    public void insertOne(OmUser omUser) {
        omUserMapper.insertOne(omUser);
    }

    @Override
    public boolean managerLogin(OmUser omUser , HttpServletRequest request) throws Exception {
//1.查找符合条件的用户列表
        OmUser user = new OmUser();
        user.setLoginName(omUser.getLoginName());
        List<OmUser> users = this.selectList(user);

        //2.判断数据库中该登录名跟密码是否跟用户输入的一致并且判断是否启用该用户
        if (users.size() > 0) {
            for (OmUser dbUser: users) {
                if (dbUser.getLoginPwd().equals(omUser.getLoginPwd()) && dbUser.getUserLock().equals("启用") && dbUser.getIdentity().equals("管理员")){
                    //3.设置session储存用户信息
                    HttpSession session = request.getSession();
                    session.setAttribute("user" , dbUser);
                    session.setMaxInactiveInterval(300000000);
                    return true;
                }
            }
        }
        return false;
    }

}
