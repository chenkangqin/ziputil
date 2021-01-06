package com.demo.zip.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName com.demo.zip.util.FileMove
 * @Description 文件移动功能
 * @Author chenkq
 * @Date 2020/11/8 23:40
 * @Version V1.0
 **/
public class FileMove {
    public static void main(String[] args) throws IOException, ZipException {
        String path = "I:/迅雷下载已上传备份资源";
        List<File> list = new ArrayList<File>();
        File file = new File(path);
        getAllMp4File(file,list);
        for (int i = 0; i < list.size(); i++) {
            File source = list.get(i);
            File dest = new File("H:/image/"+i+source.getName());
            Files.move(source.toPath(),dest.toPath());
        }

    }
    public static void getAllMp4File(File file,List<File> list){
        Stream.of(file.listFiles()).forEach(file1 -> {
            if(file1.isDirectory()){
                getAllMp4File(file1,list);
            }else if(file1.isFile() && (file1.getName().endsWith("jpg") || file1.getName().endsWith("JPG") || file1.getName().endsWith("png")|| file1.getName().endsWith("PNG") ||file1.getName().endsWith("gif")||file1.getName().endsWith("JPEG")||file1.getName().endsWith("jpeg"))){
                list.add(file1);
            }
        });

    }
}
