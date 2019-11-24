package com.ygs.imgproc;

import com.ygs.imgproc.modes.Mode;
import com.ygs.imgproc.modes.ModeFabric;
import com.ygs.imgproc.util.colorspace.RGB;
import com.ygs.imgproc.util.converter.Converter;
import com.ygs.imgproc.util.converter.GrayScaleConverter;
import com.ygs.imgproc.util.converter.HSVConverter;
import org.apache.commons.cli.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.ygs.imgproc.modes.ModeNames.CONVERTER_TYPE;
import static com.ygs.imgproc.modes.ModeNames.SCALER_TYPE;

public class Main {

    public  static Options  formOprions(){
        Options options = new Options();
        Option image = new Option("i", "image", true, "image path for operations");
        Option operation = new Option("m","mode",true,"operation type");
        Option operationMode = new Option("a","argument",true,"operation mode");
        Option output = new Option("o","output",true,"output path ");
        options.addOption(image);
        options.addOption(operation);
        options.addOption(output);
        options.addOption(operationMode);
        return options;
    }
    public static void main(String []args) throws Exception{
        BufferedImage img = null;
       // String original ="/home/igorsan98/Documents/med.jpeg";

        Options options =  formOprions();
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd ;
        try {


            cmd = parser.parse(options,args);

            if(cmd.hasOption("image")){
               String imgPath = cmd.getOptionValue("image");
               img = ModeFabric.parseImage(imgPath);
               String mode= cmd.getOptionValue("mode");
               String modeOption = cmd.getOptionValue("argument");

               Mode selected = ModeFabric.getMode(img, mode, modeOption);
               selected.activate();
               String output = imgPath.split("\\.")[0]+selected.getClass().getSimpleName();
               String format = imgPath.split("\\.")[1];
               RGB[][] rgbs = selected.getImage();
               saveImg(rgbs,output+"."+format,format,img);
            }
            else throw new ParseException("dont have image!");


        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

    }
    public static void saveImg(RGB [][]rgbs,String path,String format,BufferedImage original) throws IOException{
        BufferedImage image = new BufferedImage(rgbs.length, rgbs[0].length, BufferedImage.TYPE_INT_RGB);

        for(int x=0;x<image.getWidth();x++){
            for(int y=0;y<image.getHeight();y++) {
                try {
                    image.setRGB(x, y, rgbs[x][y].getRGB());
                }
                catch (NullPointerException e){
                    System.out.println(e.getMessage()+ String.format("`couse x=%d y=%d null" ,x,y));
                }
            }
        }
        ImageIO.write(image,format,new File(path));
    }

}
