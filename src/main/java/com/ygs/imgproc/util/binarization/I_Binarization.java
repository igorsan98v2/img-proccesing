package com.ygs.imgproc.util.binarization;

import com.ygs.imgproc.util.colorspace.RGB;

public interface I_Binarization {
    void binarizate();
    RGB[][] getBinarizated();
}
