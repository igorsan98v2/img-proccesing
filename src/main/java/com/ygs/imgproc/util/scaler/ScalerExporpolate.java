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
        scaled = new RGB[img.getWidth()*2][img.getHeight()*2];
        for(int x=0,w=0;x<img.getWidth();x++,w+=2){
            for(int y=0,z=0;y<img.getHeight();y++,z+=2){
                scaled[w][z] = rgb[x][y];
            }
        }
        for(int x=0;x<scaled.length;x++){
            for(int y=0;y<scaled[0].length;y++){
                if(x%2==0){
                    if(scaled[x][y]==null && scaled[x][y-1]!=null){
                        scaled[x][y]=scaled[x][y-1];
                    }
                }
                else if(scaled[x][y]==null && scaled[x-1][y]!=null){
                        scaled[x][y]=scaled[x-1][y];

                }
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
