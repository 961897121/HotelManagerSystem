package com.example.hotel.user.service;

import com.example.hotel.user.domain.OmUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author
 * @Date 2020/1/15 23:06
 **/
@Service
public interface UserService {
    //根据id查单个用户
    OmUser selectOneById(@Param("id")String id) throws Exception;

    //根据条件查用户列表
    List<OmUser> selectList(OmUser omUser) throws Exception;

    //验证用户登录
    boolean checkLogin(OmUser omUser , HttpServletRequest request) throws Exception;

    //新增用户方法
    boolean save(OmUser omUser) throws Exception;

    //更新用户方法
    boolean update(OmUser omUser) throws Exception;

    //根据用户修改用户
    void updateOne(OmUser omUser) throws Exception;

    //根据id删除用户
    void deleteOneById(@Param("id") String id) throws Exception;

    //插入一条数据
    void insertOne(OmUser omUser) throws Exception;

    //管理员登录
    boolean managerLogin(OmUser omUser , HttpServletRequest request) throws Exception;
}
