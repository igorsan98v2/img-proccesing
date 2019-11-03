package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.scaler.Scaler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static com.ygs.imgproc.modes.ModeNames.*;

public class ModeFabric {
    public static BufferedImage parseImage(String pathToImage) {
        BufferedImage bufferedImage =  null;
        try {
            bufferedImage =  ImageIO.read(new File(pathToImage));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return bufferedImage;
    }

    public static Mode getMode(BufferedImage img, String mode, String modeOptioon){
        Mode selected=null;
        switch (mode){
            case CONVERTER_TYPE:
                selected =new ConverterMode(modeOptioon,img);
                break;
            case SCALER_TYPE:
                selected = new ScaleMode(modeOptioon,img);
                break;
            case CONTRAST_TYPE:
                Scanner in = new Scanner(System.in);
                System.out.println("Input rMin:");
                int rMin =in.nextInt();
                System.out.println("Input gMin");
                int gMin = in.nextInt();
                System.out.println("Input bMin");
                int bMin = in.nextInt();
                System.out.println("Input rMax");
                int rMax = in.nextInt();
                System.out.println("Input gMax");
                int gMax = in.nextInt();
                System.out.println("Input bMax");
                int bMax = in.nextInt();
                RGB min = new RGB(rMin,gMin,bMin);
                RGB max = new RGB(rMax,gMax,bMax);
                selected = new ContrastMode(min,max,img);
                break;
            case BINARIZATION_TYPE: case GS_BINARIZATE:
                Integer level = Integer.parseInt(modeOptioon);
                selected = new BinarizationMode(mode,img,level);
                break;
            case BORDERS_TYPE:
                selected = null;
                break;
        }
        return selected;
    }
}
