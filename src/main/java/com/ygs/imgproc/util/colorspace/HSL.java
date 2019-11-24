package com.ygs.imgproc.util.colorspace;

public class HSL {
    double H;
    double S;
    double L;
    public HSL(){ }
    public HSL(double h,double s,double l){
        this.H = h;
        this.L = l;
        this.S = s;
    }
    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }
}
