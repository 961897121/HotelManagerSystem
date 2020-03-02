package com.example.hotel.user.mapper;

import com.example.hotel.user.domain.OmUser;
import com.example.hotel.util.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author
 * @Date 2020/1/15 22:50
 **/

@Mapper
@Repository
public interface OmUserMapper extends BaseMapper<OmUser> {

}
