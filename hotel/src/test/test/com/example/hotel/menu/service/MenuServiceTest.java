package test.com.example.hotel.menu.service;

import com.example.hotel.HotelApplication;
import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MenuServiceTest
 * @Description TODO
 * @Author
 * @Date 2020/2/3 20:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class MenuServiceTest {
    @Autowired
    private MenuService menuService;

    @Test
    public void testQueryList() throws Exception {
        Menu menu = new Menu();
        PageHelper.startPage(1,10);
        PageInfo<Menu> pageInfo = new PageInfo<>( menuService.toList(menu));
        System.out.println(pageInfo);
    }

    @Test
    public void selectTypeTest(){
//        menuService.insertType();
    }
}
