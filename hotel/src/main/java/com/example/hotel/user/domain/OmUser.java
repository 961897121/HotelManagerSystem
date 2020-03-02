package com.example.hotel.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName OmUser
 * @Description TODO(用户实体类)
 * @Author
 * @Date 2020/1/15 22:50
 **/

@Data
@ApiModel("用户实体类")
public class OmUser implements Serializable {
    @ApiModelProperty("用户Id")
    private Long id;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("登录账号")
    private String loginName;
    @ApiModelProperty("登录密码")
    private String loginPwd;
    @ApiModelProperty("年龄")
    private String age;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("身份")
    private String identity;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("是否锁定")
    private String userLock;
    @ApiModelProperty("电话号码")
    private String tel;
    @ApiModelProperty("住址")
    private String addr;
    @ApiModelProperty("qq号码")
    private String qq;
    @ApiModelProperty("微信号")
    private String weChat;
}
