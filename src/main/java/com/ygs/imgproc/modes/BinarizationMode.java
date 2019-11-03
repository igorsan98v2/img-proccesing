package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.binarization.Binariztion;
import com.ygs.imgproc.util.binarization.GrayScaleBinarization;
import com.ygs.imgproc.util.binarization.I_Binarization;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

import static com.ygs.imgproc.modes.ModeNames.BINARIZATE;
import static com.ygs.imgproc.modes.ModeNames.GS_BINARIZATE;

public class BinarizationMode implements Mode {
    I_Binarization i_binarization;
    public BinarizationMode(String option, BufferedImage bufferedImage,int level){
        switch (option){
            case BINARIZATE:
                i_binarization =  new Binariztion(bufferedImage,level);
                break;
            case GS_BINARIZATE:
                i_binarization = new GrayScaleBinarization(bufferedImage,level);
                break;
        }
    }
    @Override
    public void activate() {
        i_binarization.binarizate();
    }

    @Override
    public RGB[][] getImage() {
        return i_binarization.getBinarizated();
    }


}
