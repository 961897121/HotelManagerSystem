package com.example.hotel.cook.domain;

import com.example.hotel.menu.domain.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Cooker
 * @Description TODO
 * @Author
 * @Date 2020/2/8 17:07
 **/
@Data
@ApiModel("厨师实体类")
public class Cooker implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("厨师名称")
    private String cookName;
    @ApiModelProperty("厨师图片路径")
    private String cookImagePath;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("工资")
    private float sarlary;
    @ApiModelProperty("状态")
    private String status;

}
