package com.ygs.imgproc.util.border;

import com.ygs.imgproc.util.colorspace.RGB;

public interface EdgeDetector {
    void draw();
    RGB[][] getEdges();
}
