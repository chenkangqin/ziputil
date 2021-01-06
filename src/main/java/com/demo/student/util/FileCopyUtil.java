package com.demo.zip.util;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author chenkq
 * @version 1.0
 * @date 2020/11/9
 */
public class FileCopyUtil {
    public static void main(String[] args) throws IOException {
        String path = "D:\\share11\\jar\\trd";
        String targetFolder = "D:\\share11\\targetJar";
        File targetFile = new File(targetFolder);
        List<File> list = new ArrayList<File>();
        File file = new File(path);
        getAllMp4File(file,list);
        for (File jar: list) {
            Files.copy(jar.toPath(),new File(targetFolder+File.separator+jar.getName()).toPath());
        }
    }
    public static void getAllMp4File(File file,List<File> list){
        Stream.of(file.listFiles()).forEach(file1 -> {
            if(file1.isDirectory()){
                getAllMp4File(file1,list);
            }else if(file1.getName().endsWith("jar")){
                list.add(file1);
            }
        });

    }
    public static void copyFile(File sourceFile,File targetFile)
            throws IOException {
        // 新建文件输入流并对它进行缓冲

        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff=new BufferedInputStream(input);

        // 新建文件输出流并对它进行缓冲
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff=new BufferedOutputStream(output);

        // 缓冲数组
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len =inBuff.read(b)) != -1) {
            outBuff.write(b, 0, len);
        }
        // 刷新此缓冲的输出流
        outBuff.flush();

        //关闭流
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }
}

