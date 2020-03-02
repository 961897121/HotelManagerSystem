package com.example.hotel.cook.service;

import com.example.hotel.cook.domain.Cooker;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CookerService
 * @Description TODO
 * @Author
 * @Date 2020/2/8 17:16
 **/
@Service
public interface CookerService {
    /**
     * 分页查询厨师列表
     * @param cooker
     * @return
     */
    List<Cooker> queryList(Cooker cooker) throws Exception;

    void save(Cooker cooker) throws Exception;

    void deleteOneById(String id) throws Exception;

    void updateOne(Cooker cooker) throws Exception;

    Cooker selectOneCooker(String id) throws Exception;

    List<Cooker> getCookerList(String idStr) throws Exception;
}
