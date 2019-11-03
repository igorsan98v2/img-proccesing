package com.ygs.imgproc.util.binarization;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class Binariztion extends Operation implements I_Binarization {
    private RGB[][] binarizated;
    private int limit;
    public Binariztion(BufferedImage bufferedImage,int limit) {
        super(bufferedImage);
        this.limit = limit;
        binarizated = new RGB[img.getWidth()][img.getHeight()];
    }
    @Override
    public void binarizate(){
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){

                int bin = (rgb[x][y].r + rgb[x][y].g + rgb[x][y].b)/3;
                RGB pixel;
                if(bin>=limit){
                    pixel = new RGB(255,255,255);
                }
                else pixel = new RGB(0,0,0);
                binarizated[x][y] = pixel;
            }
        }
    }
    @Override
    public RGB[][] getBinarizated() {
        if(binarizated[0][0]==null)
            binarizate();
        return binarizated;
    }
}
