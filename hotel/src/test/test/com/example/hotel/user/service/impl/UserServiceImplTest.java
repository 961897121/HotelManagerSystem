package test.com.example.hotel.user.service.impl;

import com.example.hotel.HotelApplication;
import com.example.hotel.user.domain.OmUser;
import com.example.hotel.user.mapper.OmUserMapper;
import com.example.hotel.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class UserServiceImplTest{
    @Autowired
    private OmUserMapper omUserMapper;
    @Autowired
    private UserService userService;
//    @Test
//    void selectOneById() {
//        OmUser omUser = omUserMapper.selectOneById("4");
//        System.out.println(omUser);
//    }
//
//    @Test
//    void selectListTest(){
//        OmUser omUser = new OmUser();
//        omUser.setId(4L);
//
//        List<OmUser> omUserList = omUserMapper.selectList(omUser);
//        System.out.println(omUserList);
//    }
//
//    @Test
//    void updateOneTest(){
//        OmUser omUser = omUserMapper.selectOneById("1");
//        omUser.setLoginPwd("123456");
//
//        System.out.println(omUser);
//        omUserMapper.updateOne(omUser);
//    }
//
//    @Test
//    void insertOneTest(){
//        OmUser omUser = new OmUser();
//        omUser.setUserName("廖文斌");
//        omUser.setLoginName("liaowb");
//        omUser.setLoginPwd("123");
//        omUser.setIdentity("厨师");
//
//        System.out.println(omUser);
//        omUserMapper.insertOne(omUser);
//    }

    @Test
    public void getInfo() throws Exception {
        OmUser omUser = new OmUser();
        omUser.setLoginName("admin");
        List<OmUser> omUserList = userService.selectList(omUser);
        System.out.println(omUserList.get(0));
    }
}