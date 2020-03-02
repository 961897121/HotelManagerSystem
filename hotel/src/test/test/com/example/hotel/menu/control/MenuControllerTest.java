package test.com.example.hotel.menu.control;

import com.example.hotel.HotelApplication;
import com.example.hotel.menu.controller.MenuController;
import com.example.hotel.menu.domain.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MenuControllerTest
 * @Description TODO
 * @Author
 * @Date 2020/2/4 20:11
 **/
@SpringBootTest(classes = HotelApplication.class)
@RunWith(SpringRunner.class)
public class MenuControllerTest {
    @Autowired
    MenuController menuController;

    @Test
    public void selectListTest() throws Exception{
        Menu menu = new Menu();
        menu.setSort("蒸能量");
        menuController.toList(menu);
    }

}
