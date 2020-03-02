package com.example.hotel.menu.mapper;

import com.example.hotel.menu.domain.MenuType;
import com.example.hotel.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName MenuTypeMapper
 * @Description TODO
 * @Author
 * @Date 2020/2/13 0:38
 **/

@Repository
@Mapper
public interface MenuTypeMapper extends BaseMapper<MenuType> {
    /**
     * 根据条件查询菜单类型集合
     * @param menuType
     * @return
     */
    List<MenuType> selectTypeList(MenuType menuType);

    /**
     * 查找最大的类型id
     * @return
     */
    String selectMaxTypeId();

    /**
     * 插入菜单类型
     * @param menuType
     */
    void insertType(MenuType menuType);

    /**
     * 更新菜单类型
     * @param menuType
     */
    void updateType(MenuType menuType);

    /**
     * 删除菜单类型根据id
     * @param id
     */
    void deleteTypeById(@Param("id") String id);

    /**
     * 根据id查询菜单类型
     * @param id
     * @return
     */
    MenuType selectTypeOneById(@Param("id") String id);
}
