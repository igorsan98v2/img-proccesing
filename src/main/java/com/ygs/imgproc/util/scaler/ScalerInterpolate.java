package com.ygs.imgproc.util.scaler;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class ScalerInterpolate extends Operation implements Scaler{
    private RGB[][] scaled=null;

    public ScalerInterpolate(BufferedImage bufferedImage) {
        super(bufferedImage);

    }

    @Override
    public void scale() {
        scaled = new RGB[img.getWidth()][img.getHeight()];
        for(int x=0;x<img.getWidth()-1;x++){
            for(int y=0;y<img.getHeight()-1;y++){
                int r = (rgb[x][y].r + rgb[x+1][y].r)/2;
                int g = (rgb[x][y].g + rgb[x+1][y].g)/2;
                int b = (rgb[x][y].b + rgb[x+1][y].b)/2;
                scaled[x][y] = new RGB(r,g,b);
            }
        }
    }

    @Override
    public RGB[][] getScaled() {
        if(scaled==null){
            scale();
        }
        return scaled;
    }
}
