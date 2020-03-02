package com.example.hotel.cook.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.cook.service.CookerService;
import com.example.hotel.util.ImageFileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;


/**
 * @ClassName CookerController
 * @Description TODO
 * @Author
 * @Date 2020/2/8 17:31
 **/
@RestController
@RequestMapping("/cooker")
public class CookerController {
    private Logger logger = Logger.getLogger(CookerController.class);
    @Autowired
    private CookerService cookerService;

    /**
     * 查询厨师列表
     *
     * @param pageNum
     * @param pageSize
     * @param cooker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryList.do", method = RequestMethod.POST)
    public PageInfo<Cooker> queryList(int pageNum
            , int pageSize, Cooker cooker) throws Exception {
        logger.debug("{ 进行厨师列表的分页查询 }");
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(cookerService.queryList(cooker));
    }

    /**
     * 插入一条新厨师数据
     *
     * @param cooker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save.do" , method = RequestMethod.POST)
    public boolean saveCooker(@RequestBody Cooker cooker) throws Exception {
        logger.debug("{ 新增厨师信息 }");
        if (cooker != null){
            cookerService.save(cooker);
            return true;
        }
        return false;
    }


    /**
     * 根据id删除厨师信息
     * @param cooker
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/delete.do")
    public boolean deleteCooker(@RequestBody Cooker cooker) throws Exception{
        logger.debug("{ 正在删除厨师信息... }");
        cookerService.deleteOneById(cooker.getId() + "");
        return true;
    }

    /**
     * 更新一条厨师数据
     *
     * @param cooker
     * @return
     */
    @RequestMapping("/update.do")
    public boolean updateCooker(@RequestBody Cooker cooker) throws Exception {
        logger.debug("{ 更新厨师信息 }");
        cookerService.updateOne(cooker);
        return true;
    }

    /**
     * 根据厨师id找到该厨师
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/selectOne.do", method = RequestMethod.POST)
    public Cooker selectOneCooker(@RequestBody String id) throws Exception {
        logger.debug("{ 根据id获取厨师信息 }");
            Cooker cook = cookerService.selectOneCooker(id);
            return cook;
    }

    /**
     * 根据厨师id字符串返回list
     * @param idStr
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/getCookerList.do" , method = RequestMethod.POST)
    public List<Cooker> getCookerList(@RequestBody String idStr) throws Exception{
        logger.debug("{ 通过查询厨师id字符串，获取厨师信息List }");
        JSONObject jsonObject = JSONObject.parseObject(idStr);
        List<Cooker> cookerList = cookerService.getCookerList(jsonObject.getString("idStr"));
        return cookerList;
    }

    /**
     * 上传厨师图片，默认存放图片到指定路径
     * @param file
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/upLoad.do")
    public boolean upLoadCookImage(MultipartFile file) throws Exception{
        logger.debug("{ 正在接收厨师图片... }");
        return ImageFileUtil.saveImage(file);
    }
}
