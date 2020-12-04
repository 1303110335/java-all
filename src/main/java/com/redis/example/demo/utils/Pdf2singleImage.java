/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.utils;


import org.apache.commons.codec.binary.Base64;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuleyan
 * @version pdf2singleImage.java, v 0.1 2020-11-11 5:30 下午
 */
public class Pdf2singleImage {

    public static void main(String[] args) throws Exception {
//        Pdf2singleImage pdf2singleImage = new Pdf2singleImage();
//        String singleImageFromPDF = pdf2singleImage.createSingleImageFromPDF("testMultiPdf.pdf");


        File file = new File("out.pdf");
        InputStream inputStream = new FileInputStream(file);

        // 拿到文件 inputStream 转 pdf
//        getFile(inputStream, "out2.pdf");
        // inputstream 转 image
        ByteArrayOutputStream singleImageFromPDF = createSingleImageFromPDF(inputStream);
        // image 转base64
//        String fileContent = getFileContent("out2.jpeg");

        System.out.println(singleImageFromPDF);
    }

    /**
     * 从oss上读取文件内容
     */
    public static String getFileContent(String fileName) throws Exception {

        InputStream inputStream = new FileInputStream(new File(fileName));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] data = new byte[1024];
        int i = -1;
        BufferedOutputStream bos = new BufferedOutputStream(os);
        while ((i = bis.read(data)) != -1) {
            bos.write(data, 0, i);
        }
        bis.close();
        bos.close();
        String content = Base64.encodeBase64String((os.toByteArray()));
        inputStream.close();

        return content;
    }

    public static void getFile(InputStream is, String fileName) throws IOException {
        BufferedInputStream in=null;
        BufferedOutputStream out=null;
        in=new BufferedInputStream(is);
        out=new BufferedOutputStream(new FileOutputStream(fileName));
        int len=-1;
        byte[] b=new byte[1024];
        while((len=in.read(b))!=-1){
            out.write(b,0,len);
        }
        in.close();
        out.close();
    }


    public static ByteArrayOutputStream createSingleImageFromPDF(InputStream stream) throws Exception {

        Document document = new Document();
        document.setInputStream(stream, "");

        float scale = 2f;
        float rotation = 0f;
        List<BufferedImage> bufferImgList = new ArrayList<BufferedImage>();
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage) document.getPageImage(i, 1,
                    Page.BOUNDARY_CROPBOX, rotation, scale);
            bufferImgList.add(image);
        }
        document.dispose();

        int height = 0; // 总高度
        int width = 0; // 总宽度
        int _height = 0; // 临时的高度 , 或保存偏移高度
        int __height = 0; // 临时的高度，主要保存每个高度
        int picNum = bufferImgList.size();
        int[] heightArray = new int[picNum]; // 保存每个文件的高度
        BufferedImage buffer = null; // 保存图片流
        List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
        int[] _imgRGB; // 保存一张图片中的RGB数据
        for (int i = 0; i < picNum; i++) {
            buffer = bufferImgList.get(i);
            heightArray[i] = buffer.getHeight();// 图片高度
            _height = buffer.getHeight();
            if (i == 0) {
                width = buffer.getWidth();// 图片宽度
            }
            height += _height; // 获取总高度
            _imgRGB = new int[width * _height];// 从图片中读取RGB
            _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
            imgRGB.add(_imgRGB);
        }
        _height = 0; // 设置偏移高度为0
        // 生成新图片
        BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < picNum; i++) {
            __height = heightArray[i];
            if (i != 0) {
                _height += __height; // 计算偏移高度
            }
            imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(imageResult, "jpeg", byteArrayOutputStream);// 写图片
        return byteArrayOutputStream;
    }
}