package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.converter.*;

import java.awt.image.BufferedImage;

import static com.ygs.imgproc.modes.ModeNames.*;

public class ConverterMode implements Mode {
    Converter converter;

    public ConverterMode(String mode, BufferedImage bufferedImage){
        switch (mode){
            case  TO_GRAYSCALE:
                converter = new GrayScaleConverter(bufferedImage);
                break;
            case TO_HSV:
                converter = new HSVConverter(bufferedImage);
                break;
            case TO_HSL:

                break;
            case RGB_INVERSION:
                converter = new NegativeConverter(bufferedImage);
                break;

        }
    }
    @Override
    public void activate() {
         converter.convert();
    }

    @Override
    public RGB[][] getImage() {
        return converter.getConverted();
    }
}
