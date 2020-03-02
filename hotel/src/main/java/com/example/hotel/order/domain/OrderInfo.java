package com.example.hotel.order.domain;

import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.menu.domain.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName OrderInfo
 * @Description TODO
 * @Author
 * @Date 2020/2/19 10:49
 **/
@Data
@ApiModel("订单管理实体类")
public class OrderInfo implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("评价等级")
    private String level;
    @ApiModelProperty("菜单列表")
    private String menuList;
    @ApiModelProperty("总价")
    private float totalPrice;
    @ApiModelProperty("订单生成时间")
    private String createTime;
    @ApiModelProperty("评价")
    private String appraise;
    @ApiModelProperty("评价时间")
    private String appraiseTime;
    @ApiModelProperty("厨师列表")
    private String cookerList;
    @ApiModelProperty("用户Id")
    private String userId;
    @ApiModelProperty("菜单list")
    private List<Menu> menus;
    @ApiModelProperty("厨师list")
    private List<Cooker> cookers;
}
