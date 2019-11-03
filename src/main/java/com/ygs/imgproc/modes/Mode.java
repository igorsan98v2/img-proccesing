package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.colorspace.RGB;

public interface Mode {
    void activate();

    RGB[][] getImage();
}
