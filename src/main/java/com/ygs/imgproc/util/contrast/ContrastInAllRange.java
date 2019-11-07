package com.ygs.imgproc.util.contrast;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class ContrastInAllRange extends Operation implements Contrast {


    private RGB[][] contrast;

    private float alpha;
    private float beta;
    public ContrastInAllRange(BufferedImage bufferedImage, float alpha){
        super(bufferedImage);
        this.alpha = alpha;
        this.beta = 255*(1-alpha);

    }
    @Override
    public void makeContrast() {

        contrast = new RGB[img.getWidth()][img.getHeight()];

        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                int r = calcPreContrast(rgb[x][y].r);
                int g = calcPreContrast(rgb[x][y].g);
                int b = calcPreContrast(rgb[x][y].b);
                contrast[x][y] = new RGB(r,g,b);
            }

        }

    }
    private int calcPreContrast(int colorPart){
        return Math.round((colorPart*alpha) + beta);
    }

    @Override
    public RGB[][] getContrast() {
        if(contrast==null){
            makeContrast();
        }
        return contrast;
    }


}
