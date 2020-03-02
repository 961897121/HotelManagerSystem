package test.com.example.hotel.utils;

import com.example.hotel.HotelApplication;
import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.cook.mapper.CookerMapper;
import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.mapper.MenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName ImagePathInsertAll
 * @Description TODO
 * @Author
 * @Date 2020/2/8 11:04
 **/
@SpringBootTest(classes = HotelApplication.class)
@RunWith(SpringRunner.class)
public class ImagePathInsertAll {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private CookerMapper cookerMapper;
    @Test
    public void insertPath(){
        String path = "E:\\images\\";
        List<Menu> menuList = menuMapper.selectList(new Menu());

        for (Menu m : menuList) {
            m.setMenuImagePath(path + m.getMenuName() + ".jpg");
            menuMapper.updateOne(m);
        }
    }

    @Test
    public void insertCookPath(){
        String path = "E:\\images\\";
        List<Cooker> cookerList = cookerMapper.selectList(new Cooker());

        for (Cooker cooker : cookerList) {
            cooker.setCookImagePath(path + cooker.getCookName() + ".png");
            cookerMapper.updateOne(cooker);
        }
    }
}
