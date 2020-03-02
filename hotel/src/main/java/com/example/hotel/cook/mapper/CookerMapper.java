package com.example.hotel.cook.mapper;

import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CookerMapper
 * @Description TODO
 * @Author
 * @Date 2020/2/8 17:14
 **/
@Mapper
@Repository
public interface CookerMapper extends BaseMapper<Cooker> {

}
