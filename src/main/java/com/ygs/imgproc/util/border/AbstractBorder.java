package com.ygs.imgproc.util.border;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public abstract class AbstractBorder extends Operation implements Border {
    private RGB[][] bordered;
    public AbstractBorder(BufferedImage bufferedImage){
        super(bufferedImage);
    }

    @Override
    public RGB[][] getBorders() {
        if(bordered!=null)
            draw();
        return bordered;
    }
}
