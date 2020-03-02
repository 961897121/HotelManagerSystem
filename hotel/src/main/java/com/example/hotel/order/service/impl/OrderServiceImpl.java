package com.example.hotel.order.service.impl;

import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.cook.service.CookerService;
import com.example.hotel.menu.domain.Menu;
import com.example.hotel.menu.service.MenuService;
import com.example.hotel.order.domain.OrderInfo;
import com.example.hotel.order.mapper.OrderMapper;
import com.example.hotel.order.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/2/19 12:06
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CookerService cookerService;
    @Autowired
    private MenuService menuService;

    @Override
    public void insertOne(OrderInfo orderInfo) throws Exception {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sd.format(new Date());
        //创建时间若没有则设置当前时间为创建时间
        if (orderInfo.getCreateTime() == null || orderInfo.getCreateTime().equals("")){
            orderInfo.setCreateTime(time);
        }
        this.orderMapper.insertOne(orderInfo);
    }

    @Override
    public void deleteOneById(String id) throws Exception {
        if (id != null && !id.equals("")){
            this.orderMapper.deleteOneById(id);
        }
    }

    @Override
    public void updateOne(OrderInfo orderInfo) throws Exception {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sd.format(new Date());
        //若评价时间为空则设置评价时间为当前时间
        if (orderInfo.getAppraise() != null && !orderInfo.getAppraise().equals("")){
            if (orderInfo.getAppraiseTime() == null){
                orderInfo.setAppraiseTime(time);
            }
        }
        this.orderMapper.updateOne(orderInfo);
    }

    @Override
    public OrderInfo selectOneById(String id) throws Exception {
        OrderInfo orderInfo = getOrderInfo(this.orderMapper.selectOneById(id));
        return orderInfo;
    }

    @Override
    public List<OrderInfo> selectList(OrderInfo orderInfo) throws Exception {
        List<OrderInfo> newOrderInfo = new ArrayList<>();
        List<OrderInfo> orderInfos = orderMapper.selectList(orderInfo);
        for (OrderInfo order:orderInfos) {
            order = getOrderInfo(order);
            newOrderInfo.add(order);
        }
        return newOrderInfo;
    }

    @Override
    public PageInfo<OrderInfo> queryList( int pageNum , int pageSize , OrderInfo orderInfo) throws Exception {
        PageHelper.startPage(pageNum , pageSize);
        List<OrderInfo> orderInfos = orderMapper.selectList(orderInfo);
        PageInfo<OrderInfo> pageInfo = new PageInfo<>(orderInfos);
        return pageInfo;
    }

    public OrderInfo getOrderInfo(OrderInfo orderInfo) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (orderInfo.getCreateTime() != null && !orderInfo.getCreateTime().equals("")){
            Date parse = sdf.parse(orderInfo.getCreateTime());
            String createTime = sdf.format(parse);
            orderInfo.setCreateTime(createTime);
        }

        if (orderInfo.getAppraiseTime() != null && !orderInfo.getAppraiseTime().equals("")){
            Date parse = sdf.parse(orderInfo.getAppraiseTime());
            String appraiseTime = sdf.format(parse);
            orderInfo.setAppraiseTime(appraiseTime);
        }

        if (orderInfo.getMenuList() != null && !orderInfo.getMenuList().equals("")){
            String[] menuIds = orderInfo.getMenuList().split(",");
            List<Menu> menus = new ArrayList<>();
            for (String menuId : menuIds) {
                Menu menu = menuService.selectOneById(menuId);
                menus.add(menu);
                orderInfo.setMenus(menus);
            }
        }
        if (orderInfo.getCookerList() != null && !orderInfo.getCookerList().equals("")){
            List<Cooker> cookerList = cookerService.getCookerList(orderInfo.getCookerList());
            orderInfo.setCookers(cookerList);
        }
        return orderInfo;
    }
}
