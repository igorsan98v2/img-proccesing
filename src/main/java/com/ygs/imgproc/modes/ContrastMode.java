package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.contrast.Contrast;
import com.ygs.imgproc.util.contrast.ContrastInAllRange;
import com.ygs.imgproc.util.contrast.LinearContrast;

import java.awt.image.BufferedImage;


public class ContrastMode implements Mode {
    Contrast contrast;

    public ContrastMode(BufferedImage bufferedImage){
       contrast = new LinearContrast(bufferedImage);
    }
    public ContrastMode(BufferedImage bufferedImage,float a){
        contrast = new ContrastInAllRange(bufferedImage,a);
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
