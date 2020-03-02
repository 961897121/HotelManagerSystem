package com.example.hotel.menu.service.impl;

import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.domain.MenuType;
import com.example.hotel.menu.mapper.MenuMapper;
import com.example.hotel.menu.mapper.MenuTypeMapper;
import com.example.hotel.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/2/3 20:14
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuTypeMapper menuTypeMapper;

    @Override
    public List<Menu> toList(Menu menu) throws Exception {
        List<Menu> menuList = menuMapper.selectList(menu);
        return menuList;
    }

    @Override
    public void insertMenu(Menu menu) throws Exception  {
        menu.setStatus("启用");
        menuMapper.insertOne(menu);
    }

    @Override
    public void deleteOneMenuById(String id) throws Exception {
        menuMapper.deleteOneById(id);
    }

    @Override
    public void getPicWithName(String fileName , HttpServletResponse response) throws Exception {
        File file = new File("/images/七彩鲮鱼球.jpg");
        InputStream is = new FileInputStream(file);

        byte[] bytes = new byte[is.available()];
        int len = -1;

        while ((len = is.read(bytes)) != -1){
            response.getOutputStream().write(bytes , 0 , len);
        }
    }

    @Override
    public void updateMenu(Menu menu) throws Exception {
        menuMapper.updateOne(menu);
    }

    @Override
    public MenuType selectTypeOneById(String id) throws Exception {
        return menuTypeMapper.selectTypeOneById(id);
    }

    @Override
    public void insertType(MenuType menuType) throws Exception {
        menuTypeMapper.insertType(menuType);
    }

    @Override
    public void updateType(MenuType menuType) throws Exception {
        menuTypeMapper.updateType(menuType);
    }

    @Override
    public void deleteTypeById(String id) throws Exception {
        menuTypeMapper.deleteTypeById(id);
    }

    @Override
    public List<MenuType> selectTypeList(MenuType menuType) throws Exception {
        return menuTypeMapper.selectTypeList(menuType);
    }

    @Override
    public Menu selectOneById(String id) throws Exception {
        return changeImgPathMenu(this.menuMapper.selectOneById(id));
    }

    /**
     * 修改菜单返回实体的菜单图片，因为网页不能直接访问pc电脑的资源
     * 这里将存储数据库的图片路径 E:\images\烤猪.png -> \images\烤猪.png
     * @param menu
     * @return
     * @throws Exception
     */
    private Menu changeImgPathMenu(Menu menu) throws Exception{
        if (menu != null && menu.getMenuImagePath() != null){
            String menuImagePath = menu.getMenuImagePath();
            String newPath = menuImagePath.substring(menuImagePath.indexOf("\\"),menuImagePath.length());
            menu.setMenuImagePath(newPath);
            return menu;
        }
        return null;
    }
}
