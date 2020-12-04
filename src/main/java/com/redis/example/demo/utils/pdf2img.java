/**
 * bianque.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.redis.example.demo.utils;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xuleyan
 * @version pdf2img.java, v 0.1 2020-11-11 5:08 下午
 */
public class pdf2img {

    public static void main(String[] args) throws IOException, PDFException, PDFSecurityException, InterruptedException {
        String filePath = "/Users/xuleyan/code/redis/src/main/java/com/redis/example/demo/utils/testMultiPdf.pdf";
        Document document = new Document();
        document.setFile(filePath);
        float scale = 2.5f;//缩放比例
        float rotation = 0f;//旋转角度
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                File file = new File("/Users/xuleyan/code/redis/src/main/java/com/redis/example/demo/utils/out_" + i + ".png");
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }
}