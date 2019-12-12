package com.ygs.imgproc.modes;

public class ModeNames {
    public static final String CONVERTER_TYPE = "convert";
    public static final String SCALER_TYPE= "scale";
    public static final String BORDERS_TYPE = "edges";
    public static final String BINARIZATION_TYPE = "binarizate";
    public static final String CONTRAST_TYPE = "contrast";

    //operation options
    //converter
    public static final String TO_GRAYSCALE = "grayscale";
    public static final String TO_HSV = "hsv";
    public static final String TO_HSL = "hsl";
    public static final String RGB_INVERSION = "inversion";
    public static final String GS_INVERSION ="gs-inversion";

    //scale
    public static final String INTERPOLATE = "interpolate";
    public static final String EXTRAPOLATE = "extrapolate";


    //binarization
    public static final String BINARIZATE = "binarizate";
    public static final String GS_BINARIZATE = "gs-binarizate";

    //edges
    public static final String CANNY = "canny";
    public static final String FREICHEN = "freichen";


}
