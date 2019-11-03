package com.ygs.imgproc.util.binarization;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.converter.GrayScaleConverter;

import java.awt.image.BufferedImage;

public class GrayScaleBinarization extends Operation implements I_Binarization {
    private GrayScaleConverter grayScaleConverter;
    private Binariztion binariztion;
    private int limit;
    public  GrayScaleBinarization(BufferedImage bufferedImage,int limit){
        super(bufferedImage);
        grayScaleConverter = new GrayScaleConverter(bufferedImage);
        this.limit =  limit;
    }


    @Override
    public void binarizate() {
        grayScaleConverter.convert();
        RGB[][] converted = grayScaleConverter.getConverted();
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++) {
                img.setRGB(x,y,converted[x][y].getRGB());
            }
        }
        binariztion = new Binariztion(img,limit);
        binariztion.binarizate();
    }

    @Override
    public RGB[][] getBinarizated() {
        if(binariztion.getBinarizated()[0][0]==null){
            binarizate();
        }
        return binariztion.getBinarizated();
    }
}
