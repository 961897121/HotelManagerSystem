package com.example.hotel.menu.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Menu
 * @Description TODO
 * @Author
 * @Date 2020/1/29 0:47
 **/
@Data
@ApiModel("菜单实体类")
public class Menu implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单详情")
    private String menuDescribe;
    @ApiModelProperty("菜单状态")
    private String status;
    @ApiModelProperty("厨师编号")
    private String cookId;
    @ApiModelProperty("菜单图片路径")
    private String menuImagePath;
    @ApiModelProperty("菜单分类")
    private String sort;
    @ApiModelProperty("菜单类型id")
    private String typeId;
    @ApiModelProperty("菜单类型名称")
    private String typeName;
    @ApiModelProperty("菜单价格")
    private float typePrice;
    @ApiModelProperty("菜单分类顺序")
    private float sortId;
}
