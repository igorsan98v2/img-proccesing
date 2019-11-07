package com.ygs.imgproc.util.converter;

import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class NegativeConverter extends ConverterAbstract {

    private RGB[][] converted;
    public NegativeConverter(BufferedImage bufferedImage){
        super(bufferedImage);
    }

    @Override
    public void convert() {
        converted = new RGB[rgb.length][rgb[0].length];
        for(int i=0;i<rgb.length;i++){
            for(int j=0;j<rgb[0].length;j++){
                int rNegative = 255-rgb[i][j].r;
                int gNegative = 255-rgb[i][j].g;
                int bNegative = 255-rgb[i][j].b;
                converted[i][j] = new RGB(rNegative,gNegative,bNegative);
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
