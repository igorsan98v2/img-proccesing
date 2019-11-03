package com.ygs.imgproc.util;

import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public abstract class Operation {
    protected RGB[][]rgb;
    protected BufferedImage img;

    public Operation(BufferedImage bufferedImage){
        img = bufferedImage;
        rgb = new RGB[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for(int x=0;x<bufferedImage.getWidth();x++){
            for(int y=0;y<bufferedImage.getHeight();y++){
                rgb[x][y] = new RGB(img.getRGB(x,y));

            }
        }
    }
}
