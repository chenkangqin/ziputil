package com.demo.student.util;


import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 生成加密的压缩文件 （zip，rar 格式）
 */
public class CompressUtil {
    public static void main(String[] args) throws IOException, ZipException {
        String path = "F:/中文";
        String format = "rar";
        List<File> list = new ArrayList<File>();
        File file = new File(path);
        getAllMp4File(file,list);
        list.stream().forEach(file1 -> System.out.println(file1.getPath()));
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
        parameters.setPassword("hello");
        for (int i = 0; i < list.size(); i++) {
            File tempFile = list.get(i);
            ZipFile zipFile = new ZipFile("F:/MYZIP/"+i+".zip");
            zipFile.addFile(tempFile,parameters);
        }

//        try {
//            //generateFile(path, format);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }

    }
    public static void getAllMp4File(File file,List<File> list){
        Stream.of(file.listFiles()).forEach(file1 -> {
            if(file1.isDirectory()){
                getAllMp4File(file1,list);
            }else if(file1.getName().endsWith("txt")){
                list.add(file1);
            }
        });

    }



}