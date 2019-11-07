package com.ygs.imgproc.util.converter;

import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class NegativeGrayScaleConverter implements Converter {
    private GrayScaleConverter grayScaleConverter;
    private RGB[][] negative;
    private BufferedImage img;
    public NegativeGrayScaleConverter(BufferedImage image){
        grayScaleConverter = new GrayScaleConverter(image);
        img = image;
    }

    @Override
    public void convert() {
        grayScaleConverter.convert();
        negative = new RGB[img.getWidth()][img.getHeight()];
        RGB[][] grayscale = grayScaleConverter.getConverted();
        for(int i=0;i<grayscale.length;i++){
            for(int j=0;j<grayscale[0].length;j++){
                int inverter = 255-grayscale[i][j].r;
                negative[i][j] = new RGB(inverter,inverter,inverter);
            }
        }
    }

    @Override
    public RGB[][] getConverted() {
        if(negative[0][0]==null){
            convert();
        }
        return negative;
    }
}
