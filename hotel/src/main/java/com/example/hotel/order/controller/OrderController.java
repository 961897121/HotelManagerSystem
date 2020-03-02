package com.example.hotel.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.hotel.order.domain.OrderInfo;
import com.example.hotel.order.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName OrderController
 * @Description TODO
 * @Author
 * @Date 2020/2/19 12:05
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    private static Logger logger = Logger.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryList.do")
    public PageInfo<OrderInfo> queryList(int pageNum , int pageSize , OrderInfo orderInfo) throws Exception{
        logger.debug("{ 实现查询分页 }");
        PageInfo<OrderInfo> pageInfo = orderService.queryList(pageNum , pageSize , orderInfo);
        return pageInfo;
    }

    /**
     * 生成一条订单的方法
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save.do" , method = RequestMethod.POST)
    public boolean saveOrder(@RequestBody OrderInfo orderInfo) throws Exception{
        logger.debug("{ 有订单生成正在保存... }");
        if (orderInfo.getUserId() != null && !orderInfo.getUserId().equals("")){
            orderService.insertOne(orderInfo);
        }
        return true;
    }

    /**
     * 根据id删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteById.do")
    public boolean deleteOrderById(String id)throws Exception{
        logger.debug("{ 正在删除订单... }");
        orderService.deleteOneById(id);
        return true;
    }


    /**
     * 更新一条数据
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/update.do")
    public boolean updateOrder(@RequestBody OrderInfo orderInfo) throws Exception{
        logger.debug("{ 更新订单中... }");
        orderService.updateOne(orderInfo);
        return true;
    }

    /**
     * 根据条件查询订单列表
     * @param orderInfo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/toList.do" , method = RequestMethod.POST)
    public List<OrderInfo> getList(@RequestBody OrderInfo orderInfo) throws Exception{
        logger.debug("{ 查询订单列表... }");
        List<OrderInfo> orderInfoList = orderService.selectList(orderInfo);
        return orderInfoList;
    }

    /**
     * 根据id查询订单信息
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/selectOneById.do" , method = RequestMethod.POST)
    public OrderInfo getOrderById(@RequestBody  String id) throws Exception{
        logger.debug("{ 根据id查询订单信息 }");
        JSONObject obj = JSON.parseObject(id);
        return orderService.selectOneById(obj.getString("id"));
    }

    /**
     * 根据id查询订单信息
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/selectOne.do" , method = RequestMethod.POST)
    public OrderInfo selectOneById(@RequestBody  String id) throws Exception{
        logger.debug("{ 根据id查询订单信息 }");
        return orderService.selectOneById(id);
    }
}
