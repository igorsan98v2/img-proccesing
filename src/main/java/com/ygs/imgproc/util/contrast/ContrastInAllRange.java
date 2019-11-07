package com.ygs.imgproc.util.contrast;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class ContrastInRange extends Operation implements Contrast {
    private class InputMinMax{
        public RGB min;
        public RGB max;
    }

    private RGB[][] contrast;

    private float alpha;
    private float beta;
    public LinearContrast2(BufferedImage bufferedImage, float alpha){
        super(bufferedImage);
        this.alpha = alpha;
        this.beta = 255*(1-alpha);

    }
    @Override
    public void makeContrast() {


        initContrast();
        LinearContrast2.InputMinMax minMax = findInputMin();
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                int r = findNewColor(minMax.min.r,minMax.max.r,rgb[x][y].r);
                int g = findNewColor(minMax.min.g,minMax.max.g,rgb[x][y].g);
                int b = findNewColor(minMax.min.b,minMax.max.r,rgb[x][y].b);
                contrast[x][y] = new RGB(r,g,b);
            }

        }
    }
    private int calcPreContrast(int colorPart){
        return Math.round((colorPart*alpha) + beta);
    }
    private void initContrast(){
        contrast = new RGB[img.getWidth()][img.getHeight()];

        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                int r = calcPreContrast(rgb[x][y].r);
                int g = calcPreContrast(rgb[x][y].g);
                int b = calcPreContrast(rgb[x][y].b);
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
    private int findNewColor(int a, int b,int current ){

        return Math.round((current-a)*(255.0f/(b-a)));

    }

    private LinearContrast2.InputMinMax findInputMin(){
        int rMax=0,gMax=0,bMax=0;
        int rMin=255,gMin=255,bMin=0;
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                RGB pixel = rgb[x][y];

                if(pixel.r<rMin){
                    rMin = pixel.r;
                }
                if(pixel.r>rMax){
                    rMax = pixel.r;
                }
                if(pixel.g<gMin){
                    gMin = pixel.g;
                }
                if(pixel.g>gMax){
                    gMax = pixel.g;
                }
                if(pixel.b<bMin){
                    bMin = pixel.b;
                }
                if(pixel.b>bMax){
                    bMax = pixel.b;
                }

            }
        }

        LinearContrast2.InputMinMax minMax = new LinearContrast2.InputMinMax();
        minMax.min =  new RGB(rMin,gMin,bMin);
        minMax.max =  new RGB(rMax,gMax,bMax);
        return minMax;
    }
}
