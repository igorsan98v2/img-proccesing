package com.ygs.imgproc.util.contrast;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.HSV;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class LinearContrast extends Operation implements Contrast {
    private class InputMinMax{
        public RGB min;
        public RGB max;
    }

    private RGB[][] contrast;
    private RGB outMax;
    private RGB outMin;
    public LinearContrast(BufferedImage bufferedImage, RGB outMax, RGB outMin){
        super(bufferedImage);
        this.outMax = outMax;
        this.outMin = outMin;
    }
    @Override
    public void makeContrast() {

        contrast = new RGB[img.getWidth()][img.getHeight()];
        InputMinMax minMax = findInputMin();
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                int r = findNewColor(minMax.min.r,minMax.max.r,rgb[x][y].r,outMin.r,outMax.r);
                int g = findNewColor(minMax.min.g,minMax.max.g,rgb[x][y].g,outMin.g,outMax.g);
                int b = findNewColor(minMax.min.b,minMax.max.r,rgb[x][y].b,outMin.r,outMax.b);
                contrast[x][y] = new RGB(r,g,b);
            }

        }
    }

    @Override
    public RGB[][] getContrast() {
        if(contrast==null){
            makeContrast();
        }
        return contrast;
    }
    private int findNewColor(int inMin, int inMax,int currect, int outMin,int outMax){

        return (((currect-inMin)
                /(inMax-inMin))*(outMax-outMin)+
                outMin);
    }

    private InputMinMax  findInputMin(){
        int rMax=0,gMax=0,bMax=0;
        int rMin=255,gMin=255,bMin=0;
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                RGB pixel = rgb[x][y];

                if(pixel.r<rMin){
                    rMin = pixel.r;
                }
                else if(pixel.r>rMax){
                    rMax = pixel.r;
                }
                if(pixel.g<gMin){
                    gMin = pixel.g;
                }
                else if(pixel.g>gMax){
                    gMax = pixel.g;
                }
                if(pixel.b<bMin){
                    bMin = pixel.b;
                }
                else if(pixel.b>bMax){
                    bMax = pixel.b;
                }

            }
        }
        InputMinMax minMax = new InputMinMax();
        minMax.min =  new RGB(rMin,gMin,bMin);
        minMax.max =  new RGB(rMax,gMax,bMax);
        return minMax;
    }

}
