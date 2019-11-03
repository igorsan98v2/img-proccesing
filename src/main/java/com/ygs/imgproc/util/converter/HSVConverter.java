package com.ygs.imgproc.util.converter;

import com.ygs.imgproc.util.colorspace.HSV;
import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.RGBInRange;

import java.awt.image.BufferedImage;
import java.util.Arrays;


public class HSVConverter extends ConverterAbstract {

    private HSV [][]hsv;
    private  RGB [][] converted=null;

    public HSVConverter(BufferedImage bufferedImage){
        super(bufferedImage);
        hsv = new HSV[bufferedImage.getWidth()][bufferedImage.getHeight()];


    }
    private double getH(double min, double max, RGBInRange rgb){
        double h=0;
        if(min==max){
            return h;
        }
        else if(max ==rgb.r ){
            if(rgb.g>=rgb.b){
               h = 60.0* (rgb.g-rgb.b)/((double) (max-min))+0.0;
            }
            else {
                h = 60.0* (rgb.g-rgb.b)/((double) (max-min))+360.0;
            }

        }
        else if(max == rgb.g){
            h =60.0* (rgb.b-rgb.r)/((double) (max-min))+120.0;
        }
        else if(max == rgb.b){
            h =60.0* (rgb.r-rgb.g)/((double) (max-min))+240.0;
        }
        return  h;
    }
    private double getS(double min,double max){
        if(max!=0){
            return (max-min)/max;
        }
        return 0;
    }
    private double map(int val){
       return  (double)val/255.0;
    }
    private double[] rgbToDouble(RGB rgb){
        int [] find = {rgb.r,rgb.g,rgb.b};
        double [] findInRange= new double[3];
        for(int i=0;i<3;i++){
            findInRange[i] = map(find[i]);
        }
        return findInRange;
    }
    private HSV rgbToHSV(RGB rgb){
        double[] find = rgbToDouble(rgb);
        int[] realRgb = {rgb.r,rgb.g,rgb.b};
        Arrays.sort(find);
     //   System.out.printf("rgb(%f,%f,%f)\n",find[0],find[1],find[2]);

      //  System.out.printf("%f %f %f\n",find[0],find[1],find[2]);
        double min = find[0];
        double max = find[2];
        double[] rgbDouble = rgbToDouble(rgb);
        RGBInRange rgbInRange = new RGBInRange(rgbDouble[0],rgbDouble[1],rgbDouble[2]);
        double h = getH(min,max,rgbInRange);
        double s = getS(min,max);
        double v = max;
        return  new HSV(h,s*100,v*100);
    }
    private  int getHi(double h){
        if(h<60){
            return 0;
        }
        else if(h<120){
            return 1;
        }
        else if(h<180){
            return 2;
        }
        else if(h<240){
            return 3;
        }
        else if(h<=300)
            return 4;
        else
            return 5;

    }
    private double getVmin(double s,double v){
        return ((100-s)*v)/100.0;
    }
    private double getA(double vMin,double v,double h){
        return (v-vMin)*((h%60)/60);
    }
    private RGB hsvToRGB(HSV hsv){
        double v = hsv.v;
        double s = hsv.s;

        double r=0,g=0,b =0;
        int hi = getHi(hsv.h);
        double vMin = getVmin(s,v);
        double a =getA(vMin,v,hsv.h);
        double vInc = vMin + a;
        double vDec = v-a;
        switch (hi){
            case 0:
                r = v;
                g = vInc;
                b = vMin;
                break ;
            case 1:
                r = vDec;
                g = v;
                b = vMin;
                break;
            case 2:
                r = vMin;
                g = v;
                b = vInc;
                break;
            case 3:
                r = vMin;
                g = vDec;
                b = v;
                break;
            case 4:
                r = vInc;
                g = vMin;
                b = v;
                break;
            case 5:
                r = v;
                g = vMin;
                b = vDec;
                break;
        }
        r = r*2.55;
        g = g*2.55;
        b = b*2.55;
        return new RGB((int)r,(int)g,(int)b);
    }
    public HSV[][] toHSV(){
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                hsv[x][y]= rgbToHSV(rgb[x][y]);
            }
        }
        return hsv;
    }
    public RGB[][] fromHSV(){
        RGB[][] rgb  = new RGB[img.getWidth()][img.getHeight()];
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                rgb[x][y] = hsvToRGB(hsv[x][y]);
            }
        }
        converted = rgb;
        return rgb;
    }

    public RGB[][] getRgb() {
        return rgb;
    }

    @Override
    public void convert() {
       toHSV();

    }

    @Override
    public RGB[][] getConverted() {
        if(converted==null)
            fromHSV();
        return converted;
    }
}

