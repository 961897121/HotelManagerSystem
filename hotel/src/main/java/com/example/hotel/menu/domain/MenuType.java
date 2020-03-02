package com.example.hotel.menu.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MenuType
 * @Description TODO
 * @Author
 * @Date 2020/1/29 19:17
 **/
@Data
@ApiModel("菜单类型实体类")
public class MenuType implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("菜单类型名称")
    private String typeName;
    @ApiModelProperty("菜单价格")
    private String typePrice;
}
