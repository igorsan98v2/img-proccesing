package com.ygs.imgproc.util.contrast;

import com.ygs.imgproc.util.colorspace.RGB;

public interface Contrast {
    void makeContrast();
    RGB[][] getContrast();
}
