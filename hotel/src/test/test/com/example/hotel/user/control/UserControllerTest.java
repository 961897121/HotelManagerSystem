package test.com.example.hotel.user.control;

import com.example.hotel.HotelApplication;
import com.example.hotel.user.controller.UserController;
import com.example.hotel.user.domain.OmUser;
import com.example.hotel.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName UserControllerTest
 * @Description TODO
 * @Author
 * @Date 2020/1/23 21:07
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class UserControllerTest {
    @Autowired
    private UserService userService;
    private UserController userController;
//    @Test
//    public void checkLogin(HttpServletRequest request){
//        OmUser omUser = new OmUser();
//        omUser.setLoginName("admin");
//        omUser.setLoginPwd("123444");
//
//        boolean flag = false;
//        try {
//            flag = userService.checkLogin(omUser , request);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(flag);
//    }

    @Test
    public void getInfo() throws Exception{
        OmUser omUser = new OmUser();
        omUser.setLoginName("admin");
    }

//    @Test
//    public void saveTest() throws Exception{
//        OmUser omUser = userService.selectOneById("2");
//        omUser.setSex("男");
//
//        userController.save(omUser);
//    }
}
