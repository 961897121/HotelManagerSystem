package com.example.hotel.order.mapper;

import com.example.hotel.order.domain.OrderInfo;
import com.example.hotel.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName OrderMapper
 * @Description TODO
 * @Author
 * @Date 2020/2/19 12:04
 **/
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<OrderInfo> {

}
