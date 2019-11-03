package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.scaler.Scaler;
import com.ygs.imgproc.util.scaler.ScalerExporpolate;
import com.ygs.imgproc.util.scaler.ScalerInterpolate;

import java.awt.image.BufferedImage;

import static com.ygs.imgproc.modes.ModeNames.EXTRAPOLATE;
import static com.ygs.imgproc.modes.ModeNames.INTERPOLATE;

public class ScaleMode implements Mode{
    Scaler scaler;
    public ScaleMode(String option, BufferedImage bufferedImage){
        switch (option){
            case INTERPOLATE:
                scaler = new ScalerInterpolate(bufferedImage);
                break;
            case EXTRAPOLATE:
                scaler = new ScalerExporpolate(bufferedImage);
                break;
        }

    }

    @Override
    public void activate() {
        scaler.scale();
    }

    @Override
    public RGB[][] getImage() {
        return scaler.getScaled();
    }
}
