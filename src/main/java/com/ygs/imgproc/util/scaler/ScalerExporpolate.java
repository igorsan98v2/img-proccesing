package com.ygs.imgproc.util.scaler;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class ScalerExporpolate extends Operation implements Scaler{
    private RGB[][] scaled;
    public ScalerExporpolate(BufferedImage bufferedImage){
        super(bufferedImage);
    }

    @Override
    public void scale() {
        scaled = new RGB[img.getWidth()][img.getHeight()];
        for(int x=0,i=0;x<img.getWidth()/2;x++,i+=2){
            for(int y=0,j=0;y<img.getHeight()/2;y++,j+=2){
                int r = (rgb[x][y].r);
                int g = (rgb[x][y].g);
                int b = (rgb[x][y].b);
                scaled[i][j] = new RGB(r,g,b);
                scaled[i+1][j] = new RGB(r,g,b);
                scaled[i+i][j+1] = new RGB(r,g,b);
                scaled[i][j+1] = new RGB(r,g,b);

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
