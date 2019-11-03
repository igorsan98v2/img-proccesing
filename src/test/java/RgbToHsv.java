import com.ygs.imgproc.util.colorspace.HSV;
import com.ygs.imgproc.util.converter.HSVConverter;
import com.ygs.imgproc.util.colorspace.RGB;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import static org.junit.Assert.assertArrayEquals;


public class RgbToHsv {
    /**
     * @autor Ihor Yutsyk
     * {@link #testRGBtoRGB()} is valid only for
     * <a href="file:../resources/Three_8x8.png ">/resources/Three_8x8.png</a>file
     * if you by some reason do not have one, restore it from rgbOriginal variable
     *
     * **/
    @Test
    public void testRGBtoRGB(){
        BufferedImage img = null;
        int [] rgbOriginal = {
                0x0c0820,0x0c0820,0x2e3b2c,0x354530,0x354631,0x1d2721,0x0c0820,0x0c0820,
                0x0c0820,0x2c392b,0x4d6440,0x4a603c,0x4d643f,0x49603b,0x1f2827,0x0c0820,
                0x0c0820,0x3e5235,0x435934,0x40542c,0x42562e,0x49603b,0x3f5334,0x0c0820,
                0x0c0820,0x475e39,0x42572f,0x3f4f2a,0x3a3d22,0x41552e,0x3c512a,0x0c0820,
                0x0c0820,0x4a613f,0x445933,0x3a3e21,0x352618,0x3a4d26,0x314223,0x0c0820,
                0x0c0820,0x232d29,0x2d3c28,0x2f291b,0x362618,0x0c0820,0x0c0820,0x0c0820,
                0x0c0820,0x0c0820,0x0c0820,0x271c1c,0x362618,0x0c0820,0x0c0820,0x0c0820,
                0x0c0820,0x0c0820,0x0c0820,0x271c1c,0x362618,0x0c0820,0x0c0820,0x0c0820
        };
        int [] rgbAfterDevided = null;

        try {
            img = ImageIO.read(new File("/home/igorsan98/Documents/WorkSpace/Math_Methods_In_BioTechSystems/Three_8x8.png"));
            rgbAfterDevided = new int [img.getHeight()*img.getWidth()];
            for(int y=0,z=0;y<img.getHeight();y++){
                for(int x=0;x<img.getWidth();x++,z++) {
                    RGB rgb = new RGB(img.getRGB(x,y));
                    rgbAfterDevided[z]= rgb.getRGB();
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

        assertArrayEquals(rgbOriginal,rgbAfterDevided);
    }
    @Test
    public void testRGBtoHSV(){
        HSV[] hsvs = {
                new HSV(250,75,12.5),
                new HSV(250,75,12.5),
                new HSV(112,25.4,23.1),
                new HSV(105.7,30.4,27.1),
                new HSV(108.6,30,27.5),
                new HSV(144,25.6,15.3),
                new HSV(250,75,12.5),
                new HSV(250,75,12.5)
        };
        BufferedImage img;
        HSV[] fromImage = null;
        try {
            img = ImageIO.read(new File("/home/igorsan98/Documents/WorkSpace/Math_Methods_In_BioTechSystems/Three_8x8.png"));
            fromImage = new HSV [8];
            HSVConverter converter = new HSVConverter(img);
            HSV[][] converted = converter.toHSV();
            for(int i=0;i<8;i++){
                fromImage[i]=converted[i][0];
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        assertArrayEquals(hsvs,fromImage);

    }
    @Test
    public void testRGBtoHSVtoRGB(){
        RGB[][] fromImage =null;
        RGB[][] fromConverter =null;
        BufferedImage img;
        try {
            img = ImageIO.read(new File("/home/igorsan98/Documents/WorkSpace/Math_Methods_In_BioTechSystems/Three_8x8.png"));


            HSVConverter converter = new HSVConverter(img);
            fromImage = converter.getRgb();
            converter.toHSV();

            fromConverter = converter.fromHSV();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        assertArrayEquals(fromImage,fromConverter);
    }
}
