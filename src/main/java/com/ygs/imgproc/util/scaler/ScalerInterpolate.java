package com.ygs.imgproc.util.scaler;

import com.ygs.imgproc.util.Operation;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

public class ScalerInterpolate extends Operation implements Scaler{
    private RGB[][] scaled=null;

    public ScalerInterpolate(BufferedImage bufferedImage) {
        super(bufferedImage);

    }

    /*i:0123
        0246
      i:0123456
        0123456

    */

    @Override
    public void scale() {
        scaled = new RGB[img.getWidth()*2][img.getHeight()*2];
        for(int x=0,w=0;x<img.getWidth();x++,w+=2){
            for(int y=0,z=0;y<img.getHeight();y++,z+=2){


                scaled[w][z] = rgb[x][y];

            }
        }

        for(int z=0;z<2;z++) {
            for (int x = 0; x < scaled.length; x++) {

                for (int y = 0; y < scaled[0].length; y++) {
                    if (scaled[x][y] == null && y > 0 && y + 1 < scaled[0].length - 1 && scaled[x][y + 1] != null) {
                        int r = (scaled[x][y - 1].r + scaled[x][y + 1].r) / 2;
                        int g = (scaled[x][y - 1].g + scaled[x][y + 1].g) / 2;
                        int b = (scaled[x][y - 1].b + scaled[x][y + 1].b) / 2;
                        scaled[x][y] = new RGB(r, g, b);
                    }
                    if (scaled[x][y] == null && x > 0 && x + 1 < scaled.length - 1 && scaled[x + 1][y] != null) {
                        int r = (scaled[x - 1][y].r + scaled[x + 1][y].r) / 2;
                        int g = (scaled[x - 1][y].g + scaled[x + 1][y].g) / 2;
                        int b = (scaled[x - 1][y].b + scaled[x + 1][y].b) / 2;
                        scaled[x][y] = new RGB(r, g, b);
                    }


                }

            }
        }
        for(int i=0,j=scaled[0].length-1;i<scaled.length-1;i++){
            int r = scaled[i][j-1].r*2 - scaled[i][j-2].r ;
            int g = scaled[i][j-1].g*2 - scaled[i][j-2].g ;
            int b = scaled[i][j-1].b*2 - scaled[i][j-2].b ;
            scaled[i][j] = new RGB(r, g, b);
        }

        for(int j=0,i=scaled.length-1;j<scaled[0].length;j++){
            int r = scaled[i-1][j].r*2 - scaled[i-2][j].r ;
            int g = scaled[i-1][j].g*2 - scaled[i-2][j].g ;
            int b = scaled[i-1][j].b*2 - scaled[i-2][j].b ;

            scaled[i][j] = new RGB(r, g, b);
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
