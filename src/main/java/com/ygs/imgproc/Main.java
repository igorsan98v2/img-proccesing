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
            else throw new Exception("dont have image!");

            /*img = ImageIO.read(new File(original));
            Converter hsvConverter = new GrayScaleConverter(img);
            BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
            hsvConverter.convert();
            RGB[][] rgbs = hsvConverter.getConverted();
            for(int x=0;x<img.getWidth();x++){
                for(int y=0;y<img.getHeight();y++) {

                    image.setRGB(x,y,rgbs[x][y].getRGB());

                }
            }

            /*
            for(int x=0;x<img.getWidth();x++){
                for(int y=0;y<img.getHeight();y++) {
                    RGB rgb = new RGB(img.getRGB(x,y));

                    image.setRGB(x,y,rgb.getRGB());

                }
            }
            */



            //ImageIO.write(image, "png", new File("/home/igorsan98/Documents/Untitled01.png"));

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
        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x=0;x<original.getWidth();x++){
            for(int y=0;y<original.getHeight();y++) {

                image.setRGB(x,y,rgbs[x][y].getRGB());

            }
        }
        ImageIO.write(image,format,new File(path));
    }

}
