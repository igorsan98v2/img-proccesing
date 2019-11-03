package com.ygs.imgproc.util.converter;

import com.ygs.imgproc.util.colorspace.HSV;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class GrayScaleConverter extends ConverterAbstract {

    private RGB[][] converted;
    public GrayScaleConverter(BufferedImage bufferedImage){
        super(bufferedImage);
        converted = new RGB[bufferedImage.getWidth()][bufferedImage.getHeight()];
    }

    @Override
    public void convert() {

       for(int i=0;i<rgb.length;i++){
           for(int j=0;j<rgb[0].length;j++){
               int grayScale = (int)(0.299*rgb[i][j].r + 0.587*rgb[i][j].g + 0.114*rgb[i][j].b);
               converted[i][j] = new RGB(grayScale,grayScale,grayScale);
           }
       }

    }

    @Override
    public RGB[][] getConverted() {
        if(converted[0][0]==null)
            convert();
        return converted;
    }
}
