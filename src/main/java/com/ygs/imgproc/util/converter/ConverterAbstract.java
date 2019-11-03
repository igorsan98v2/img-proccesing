package com.ygs.imgproc.util.converter;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public abstract class  ConverterAbstract extends Operation implements Converter  {
    public ConverterAbstract(BufferedImage bufferedImage){
       super(bufferedImage);
    }
}
