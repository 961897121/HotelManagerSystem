package com.example.hotel.util;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName BaseMapper
 * @Description TODO(CURD模板类 ， 该类是接口类 ， 子类负责CURD增删查改的实现)
 * @Author
 * @Date 2020/1/15 22:51
 **/

public interface BaseMapper<T> {
    /**
     * 插入一条数据的方法
     * @param t （泛型实体类）
     */
    void insertOne(T t);

    /**
     * 更新一条数据的方法
     * @param t
     */
    void updateOne(T t);

    /**
     * 按id查找一条数据
     * @param id
     * @return
     */
    T selectOneById(@Param("id") String id);

    /**
     * 按id删除一条数据
     * @param id
     */
    void deleteOneById(@Param("id") String id);

    /**
     * 根据条件查数据，返回list集合
     * @param t
     * @return
     */
    List<T> selectList(T t);
}
