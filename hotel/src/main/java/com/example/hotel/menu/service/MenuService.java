package com.example.hotel.menu.service;

import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.domain.MenuType;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName MenuService
 * @Description TODO
 * @Author
 * @Date 2020/2/3 20:13
 **/
@Service
public interface MenuService {
    //查询菜单列表
    List<Menu> toList(Menu menu) throws Exception ;

    //插入一条新菜单
    void insertMenu(Menu menu) throws Exception ;

    //删除一条菜单
    void deleteOneMenuById(String id) throws Exception ;

    //同步图片
    void getPicWithName(String fileName , HttpServletResponse response)throws Exception ;

    //更新菜单信息
    void updateMenu(Menu menu) throws  Exception;

    //根据id查找类型
    MenuType selectTypeOneById(String id) throws Exception;

    //插入菜单
    void insertType(MenuType menuType) throws Exception;

    //更新菜单类型
    void updateType(MenuType menuType) throws Exception;

    //删除菜单类型
    void deleteTypeById(String id) throws Exception;

    //返回类型list
    List<MenuType> selectTypeList(MenuType menuType) throws Exception;

    //根据id查找一条菜单
    Menu selectOneById(String id) throws Exception;
}
