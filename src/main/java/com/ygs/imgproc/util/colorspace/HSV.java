package com.ygs.imgproc.util.colorspace;

import java.util.Objects;

public class HSV {
    public final double h;
    public final double s;
    public final double v;
    public static final  double ACC_LIMIT=0.5;//percent
    public HSV( double h,double s,double v){
        this.h = h;
        this.s = s;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HSV hsv = (HSV) o;
        return (Math.abs(h-hsv.h)<=ACC_LIMIT)&&
                (Math.abs(s-hsv.s)<=ACC_LIMIT)&&
                (Math.abs(v-hsv.v)<=ACC_LIMIT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, s, v);
    }

    @Override
    public String toString() {
        return String.format("h=%f s=%f v=%f",h,s,v);
    }
}
