package com.ygs.imgproc.util.border;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.converter.GrayScaleConverter;

import java.awt.image.BufferedImage;

public abstract class AbstractEdgeDetector extends Operation implements EdgeDetector {
    protected RGB[][] edged;
    public AbstractEdgeDetector(BufferedImage bufferedImage){
        super(bufferedImage);

    }

    @Override
    public RGB[][] getEdges() {
        if(edged !=null)
            draw();
        return edged;
    }
}
