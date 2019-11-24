package com.ygs.imgproc.modes;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


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
                if(modeOptioon!=null){
                    float a = Float.parseFloat(modeOptioon);
                    selected = new ContrastMode(img,a);
                }
                else selected = new ContrastMode(img);
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
