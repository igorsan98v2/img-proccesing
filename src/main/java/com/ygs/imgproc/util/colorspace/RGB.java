package com.ygs.imgproc.util.colorspace;

import java.util.Objects;

public class RGB {
    public final int r;
    public final  int g;
    public final int b;
    public static final int ACC_LIMIT=5;// 5/255 it is only 1.96% mistake
    public RGB(int r,int g,int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public RGB(int rgb){
        this.r = rgb >> 16 & 0xFF;
        this.g = rgb >> 8 & 0xFF;
        this.b = rgb  & 0xFF;
    }
    public  int getRGB(){
       return  (r<<16&0xFF0000|g<<8&0x00FF00|b&0x0000FF);

       // System.out.println(String.format("#%02x%02x%02x %d", r, g, b,(r<<16&0xFF0000|g<<8&0x00FF00|b&0x0000FF)));
       // return Integer.parseInt(String.format("%02x%02x%02x", r, g, b),16);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGB rgb = (RGB) o;
        return Math.abs(r- rgb.r)<=ACC_LIMIT &&
               Math.abs(g - rgb.g)<=ACC_LIMIT &&
               Math.abs( b - rgb.b)<=ACC_LIMIT;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }

    @Override
    public String toString() {
        return String.format("rgb(%3d, %3d, %3d)",r,g,b);
    }
}
