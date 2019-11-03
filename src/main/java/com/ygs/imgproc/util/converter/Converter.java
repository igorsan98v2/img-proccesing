package com.ygs.imgproc.util.converter;


import com.ygs.imgproc.util.colorspace.RGB;

public interface Converter {
    void convert();
    RGB[][] getConverted();
}
