package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.contrast.Contrast;
import com.ygs.imgproc.util.contrast.LinearContrast;

import java.awt.image.BufferedImage;


public class ContrastMode implements Mode {
    Contrast contrast;

    public ContrastMode(RGB min, RGB max, BufferedImage bufferedImage){
       contrast = new LinearContrast(bufferedImage,min,max);
    }

    @Override
    public void activate() {
        contrast.makeContrast();
    }

    @Override
    public RGB[][] getImage() {
        return contrast.getContrast();
    }
}
