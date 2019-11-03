package com.ygs.imgproc.util.scaler;

import com.ygs.imgproc.util.colorspace.RGB;

public interface Scaler {
    void scale();
    RGB[][] getScaled();
}
