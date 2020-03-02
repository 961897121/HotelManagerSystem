package com.example.hotel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ImageFileUtil {
    //默认图片存放路径
    private final static String savePath = "E:\\images\\";

    public static boolean saveImage(MultipartFile file) throws Exception{
        if (!file.isEmpty()){
            //获取图片名称
            String fileName = file.getOriginalFilename();
            //获取图片后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."),fileName.length());
            if (suffixName.equals(".png") || suffixName.equals(".jpg")){
                File imgFile = new File(savePath + fileName);
                file.transferTo(imgFile);
                return true;
            }
        }
        return false;
    }
}
