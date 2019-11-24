package com.ygs.imgproc.util.converter;

import com.ygs.imgproc.util.RGBInRange;
import com.ygs.imgproc.util.colorspace.HSL;
import com.ygs.imgproc.util.colorspace.HSV;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class HSLConverter extends ConverterAbstract {
    private enum Crit{
        R,
        G,
        B,
    }
    private class Pair{
        double value;
        Crit type;

        public Pair(double value, Crit type) {
            this.value = value;
            this.type = type;
        }

        Pair(){

        }

    }
    private HSL[][]hsl;
    private  RGB [][] converted=null;
    private RGBInRange[][]rgbFrom0To1=null;

    public HSLConverter(BufferedImage bufferedImage) {
        super(bufferedImage);
        rgbFrom0To1 = new RGBInRange[bufferedImage.getWidth()][bufferedImage.getHeight()];
        converted =  new RGB[bufferedImage.getWidth()][bufferedImage.getHeight()];
        hsl = new HSL[bufferedImage.getWidth()][bufferedImage.getHeight()];
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                double r = rgb[x][y].r/255.0;
                double g = rgb[x][y].g/255.0;
                double b = rgb[x][y].b/255.0;
                rgbFrom0To1[x][y]=new RGBInRange(r,g,b);
            }
        }
    }
    private Pair findMaxC(RGBInRange rgb){
        if(rgb.r > rgb.b){
            if(rgb.r > rgb.g ){
                return new Pair(rgb.r,Crit.R);
            }
            else return new Pair(rgb.g,Crit.G);
        }
        else if(rgb.b > rgb.g){
            return new Pair(rgb.b,Crit.B);
        }
        else return new Pair(rgb.g,Crit.G);

    }
    private Pair findMinC(RGBInRange rgb){
        if(rgb.r < rgb.b){
            if(rgb.r < rgb.g ){
                return new Pair(rgb.r,Crit.R);
            }
            else return new Pair(rgb.g,Crit.G);
        }
        else if(rgb.b < rgb.g){
            return new Pair(rgb.b,Crit.B);
        }
        else return new Pair(rgb.g,Crit.G);
    }
    private double findH(RGBInRange rgb,Pair min,Pair max){

        double delta = max.value - min.value;
        if(max.value == 0){
            return 0;
        }
        double h = 0;
        switch (max.type){
            case R:
                h =  60*(((rgb.g-rgb.b)/delta)%6);
                break;
            case G:
                h = 60*(((rgb.b-rgb.r)/delta)+2);
                break;
            case B:
                h = 60*(((rgb.r-rgb.g)/delta)+4);
                break;
                default:return 0;

        }
        if(h>0)
            return h;
        else {
            return  360-h;
        }
    }
    private RGB backConvert(HSL hsl){
        double c = (1- Math.abs(2*hsl.getL()-1))*hsl.getS();
        double x = c*(1-Math.abs(hsl.getH()/60%2-1));
        double m = hsl.getL() - (c/2);
        RGBInRange rgb;
        if(hsl.getH()<60){
            rgb = new RGBInRange(c,x,0);
        }
        else if(hsl.getH()<120){
            rgb = new RGBInRange(x,c,0);
        }
        else if(hsl.getH()<180){
            rgb = new RGBInRange(0,c,x);
        }
        else if(hsl.getH()<240){
            rgb = new RGBInRange(0,x,c);
        }
        else if(hsl.getH()<300){
            rgb = new RGBInRange(x,0,c);
        }
        else  rgb = new RGBInRange(c,0,x);
        int r = (int)((rgb.r+m)*255.0);
        int g = (int)((rgb.g+m)*255.0);
        int b = (int)((rgb.b+m)*255.0);
        return new RGB(r,g,b);

    }
    @Override
    public void convert() {
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                Pair max = findMaxC(rgbFrom0To1[x][y]);
                Pair min = findMinC(rgbFrom0To1[x][y]);
                double delta =max.value - min.value;
                double h = findH(rgbFrom0To1[x][y],min,max);
                double l = (max.value + min.value)/2;
                double s = delta==0?0: delta/(1-Math.abs(2*l-1));
                hsl[x][y] = new HSL(h,s,l);
                converted[x][y]= backConvert(hsl[x][y]);
            }
        }

    }

    @Override
    public RGB[][] getConverted() {
        if (converted[0][0]== null) {
            return converted;
        }
        else convert();
        return converted;
    }
}
