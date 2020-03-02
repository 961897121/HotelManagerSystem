package com.example.hotel.order.service;

import com.example.hotel.order.domain.OrderInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author
 * @Date 2020/2/19 12:05
 **/
@Service
public interface OrderService {
    void insertOne(OrderInfo orderInfo) throws Exception;

    void deleteOneById(String id) throws Exception;

    void updateOne(OrderInfo orderInfo) throws Exception;

    OrderInfo selectOneById(String id)throws Exception;

    List<OrderInfo> selectList(OrderInfo orderInfo) throws Exception;

    PageInfo<OrderInfo> queryList(int pageNum , int pageSize , OrderInfo orderInfo) throws Exception;
}
