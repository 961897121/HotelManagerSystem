package test.com.example.hotel.menu.dao;

import com.example.hotel.HotelApplication;
import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.domain.MenuType;
import com.example.hotel.menu.mapper.MenuMapper;
import com.example.hotel.menu.mapper.MenuTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName MenuMapperTest
 * @Description TODO
 * @Author
 * @Date 2020/2/1 21:35
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class MenuMapperTest {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuTypeMapper menuTypeMapper;

    @Test
    public void selectOneTest(){
        Menu menu = menuMapper.selectOneById("1");
        System.out.println(menu);
    }

    @Test
    public void selectListTest(){
        Menu menu = new Menu();
//        menu.setId(2L);
//        menu.setMenuName("金牌蝦饺皇");
        List<Menu> menuList = menuMapper.selectList(menu);
        System.out.println(menuList);
    }

    @Test
    public void insertOneTest(){
        Menu menu = new Menu();
        menu.setMenuName("烤猪");
        menu.setMenuDescribe("烤猪很香");
        menu.setStatus("启用");
        menu.setSort("蒸能量");
//        menu.setTypeId("4");

//        Menu menu = new Menu();
        menu.setTypeName("贵");
//        menu.setTypePrice(123);
        menu.setTypeId("10");

        menuMapper.insertOne(menu);
    }

    @Test
    public void updateOneTest(){
        Menu menu = new Menu();
        menu.setTypeName("贵");
        menu.setTypePrice(616);
        menu.setTypeId("10");

//        Menu menu = new Menu();
        menu.setId(95L);
        menu.setMenuName("烤蝙蝠");
        menu.setMenuDescribe("野味香");
        menu.setStatus("启用");
        menu.setSort("蒸能量");
//        menu.setTypeId("10");

        menuMapper.updateOne(menu);
    }

    @Test
    public void selectTypeTest(){
        menuTypeMapper.selectMaxTypeId();
    }

    @Test
    public void selectTypeListTest(){
        MenuType menuType = new MenuType();
//        menuType.setTypeName("小");
        List<MenuType> menuTypes = menuTypeMapper.selectTypeList(menuType);
//        PageHelper.startPage(1,5);
//        PageInfo<MenuType> pageInfo = new PageInfo<>(menuTypes);
        System.out.println();
    }
}
