package com.example.hotel.menu.controller;

import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.cook.service.CookerService;
import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.domain.MenuType;
import com.example.hotel.menu.service.MenuService;
import com.example.hotel.util.ImageFileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @ClassName MenuController
 * @Description TODO
 * @Author
 * @Date 2020/1/29 18:15
 **/

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private CookerService cookerService;
    private static Logger logger = Logger.getLogger(MenuController.class);

    /**
     * 分页查询菜单列表
     * @param pageNum（第几页）
     * @param pageSize（每页显示多少条）
     * @param menu（菜单实体类根据条件查找）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList.do" , method = RequestMethod.POST)
    public PageInfo<Menu> queryList( int pageNum
            , int pageSize , Menu menu ) throws Exception{
        logger.debug("{ 查询查菜单列表... }");
        PageHelper.startPage( pageNum , pageSize );
        PageInfo<Menu> pageInfo =  new PageInfo<>(menuService.toList(menu));
        return pageInfo;
    }

    /**
     * android客户端获取信息的方法
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/toList.do" , method = RequestMethod.POST)
    public List<Menu> toList(@RequestBody Menu menu ) throws Exception{
        logger.debug("{ 查询菜单列表... }");
        List<Menu> menuList = menuService.toList(menu);
        return menuList;
    }

    /**
     * 插入一条菜单
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save.do")
    public boolean save(@RequestBody  Menu menu) throws Exception{
        logger.debug("{ 正在添加新菜单 }");
        menuService.insertMenu(menu);
        return true;
    }

    /**
     * 更新菜单
     * @param menu
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/update.do")
    public boolean update(@RequestBody Menu menu) throws Exception{
        logger.debug("{ 更新菜单信息中... }");
        menuService.updateMenu(menu);
        return true;
    }

    /**
     * 根据","去分割菜单负责的厨师id，通过厨师id找到该厨师信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCookerList.do")
    public Object[] getCookerNameList(@RequestBody String id) throws Exception{
        List<Cooker> cookerNameList = cookerService.getCookerList(id);
        return cookerNameList.toArray();
    }


    /**
     * 删除一条菜单的方法
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete,do")
    public boolean deleteMenu(@RequestParam("id") String id) throws Exception{
        logger.debug("{ 正在删除菜单... }");
        if (!StringUtils.isEmpty(id)){
            menuService.deleteOneMenuById(id);
        }
        return true;
    }

    /**
     * 下载图片
     * @param picName
     * @param response
     * @throws Exception
     */
    @RequestMapping("/comparePic.do")
    public void updatePic(String picName , HttpServletResponse response) throws Exception{
        logger.debug("{ 正在同步图片... }");
        menuService.getPicWithName("" , response);
    }

    /**
     * 开启查找菜单类型的分页
     * @param pageNum
     * @param pageSize
     * @param menuType
     */
    @RequestMapping("/selectTypeList.do")
    public PageInfo<MenuType> selectTypeList(int pageNum
            , int pageSize , MenuType menuType) throws Exception{
        logger.debug("{ 正在执行查找菜单类型分页... }");
        PageHelper.startPage(pageNum , pageSize);
        List<MenuType> menuTypes = menuService.selectTypeList(menuType);
        PageInfo<MenuType> pageInfo = new PageInfo<>(menuTypes);
        return pageInfo;
    }

    /**
     * 根据menuType的typeName去查询菜单类型信息
     * @param menuType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/menuType.do")
    public MenuType getMenuType(@RequestBody MenuType menuType) throws Exception{
        logger.debug("{ 查询该菜单类型的信息 }");
        List<MenuType> menuTypes = menuService.selectTypeList(menuType);
        if (menuTypes.size() > 0){
            return menuTypes.get(0);
        }
        return null;
    }

    /**
     * 根据菜单类型id删除该id对应的数据
     * @param menuType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/deleteType.do")
    public boolean deleteType(@RequestBody MenuType menuType) throws Exception{
        logger.debug("{ 正在删除菜单类型... }");
        if (menuType.getId() != null){
            menuService.deleteTypeById(menuType.getId() + "");
            return true;
        }
        return false;
    }

    /**
     * 更新价格类型的方法
     * @param menuType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/updateType.do")
    public boolean updateType(@RequestBody MenuType menuType) throws Exception{
        logger.debug("{ 正在更新菜单类型... }");
        if (menuType != null){
            menuService.updateType(menuType);
            return true;
        }
        return false;
    }

    /**
     *
     * 增加价格类型的方法
     * @param menuType
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/saveType.do")
    public boolean saveType(@RequestBody MenuType menuType) throws Exception{
        if (menuType.getTypeName() != null){
            menuService.insertType(menuType);
            return true;
        }
        return false;
    }

    /**
     * 根据类型ID查询价格类型
     */
    @ResponseBody
    @RequestMapping("/selectTypeByTypeId.do")
    public MenuType selectTypeByTypeId(Menu menu) throws Exception{
        logger.debug("{ 正在获取价格类型中... }");
        MenuType menuType = menuService.selectTypeOneById(menu.getId() + "");
        return menuType;
    }

    /**
     * 实现菜单文件上传的接收，此时会存放图片到指定路径
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upLoad.do")
    public boolean upLoad(MultipartFile file) throws Exception{
        logger.debug("{ 正在接收菜单图片... }");
        return ImageFileUtil.saveImage(file);
    }
}
