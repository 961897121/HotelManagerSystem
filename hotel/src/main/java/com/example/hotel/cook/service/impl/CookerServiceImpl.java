package com.example.hotel.cook.service.impl;

import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.cook.mapper.CookerMapper;
import com.example.hotel.cook.service.CookerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CookerServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/2/8 17:16
 **/
@Service
public class CookerServiceImpl implements CookerService {
    @Autowired
    private CookerMapper cookerMapper;

    @Override
    public List<Cooker> queryList(Cooker cooker) throws Exception {
        List<Cooker> cookerList = cookerMapper.selectList(cooker);
        List<Cooker> cookers = new ArrayList<>();
        for (Cooker cook : cookerList) {
            Cooker c = changeImgPathCooker(cook);
            cookers.add(c);
        }
        return cookerList;
    }

    @Override
    public void save(Cooker cooker) throws Exception {
        cooker.setStatus("启用");
        cookerMapper.insertOne(cooker);
    }

    @Override
    public void deleteOneById(String id) throws Exception {
        cookerMapper.deleteOneById(id);
    }

    @Override
    public void updateOne(Cooker cooker) throws Exception {
        cookerMapper.updateOne(cooker);
    }

    @Override
    public Cooker selectOneCooker(String id) throws Exception {
        Cooker cooker = cookerMapper.selectOneById(id);
        return changeImgPathCooker(cooker);
    }

    @Override
    public List<Cooker> getCookerList(String idStr) throws Exception {
        List<Cooker> cookerList = new ArrayList<>();
        //判断该字符串是否用逗号接厨师id的格式，这里统一格式  ,3,4,5

        if (idStr.contains(",")){
            String[] strings = idStr.split(",");
            for (int i = 0 ; i < strings.length ; i ++){
                Cooker cooker = this.selectOneCooker(strings[i]);
                cookerList.add( changeImgPathCooker(cooker));
            }
        }else {
            Cooker cooker = this.selectOneCooker(idStr.split(",")[0]);
            cookerList.add(changeImgPathCooker(cooker));
        }
        return cookerList;
    }

    /**
     * 修改厨师返回实体的厨师图片，因为网页不能直接访问pc电脑的资源
     * 这里将存储数据库的图片路径 E:\images\王厨师.png -> \images\王厨师.png
     * @param cooker
     * @return
     * @throws Exception
     */
    private Cooker changeImgPathCooker(Cooker cooker) throws Exception{
        if (cooker != null && cooker.getCookImagePath() != null){
            String cookImagePath = cooker.getCookImagePath();
            String newPath = cookImagePath.substring(cookImagePath.indexOf("\\"),cookImagePath.length());
            cooker.setCookImagePath(newPath);
            return cooker;
        }
        return null;
    }
}
