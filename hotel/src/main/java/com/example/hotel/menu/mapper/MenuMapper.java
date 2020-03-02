package com.example.hotel.menu.mapper;

import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.domain.MenuType;
import com.example.hotel.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName MenuMapper
 * @Description TODO
 * @Author
 * @Date 2020/1/29 0:47
 **/
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

}
