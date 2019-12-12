package com.ygs.imgproc.modes;

import com.ygs.imgproc.util.binarization.Binariztion;
import com.ygs.imgproc.util.binarization.GrayScaleBinarization;
import com.ygs.imgproc.util.border.AbstractEdgeDetector;
import com.ygs.imgproc.util.border.CannyEdgeDetecor;
import com.ygs.imgproc.util.border.EdgeDetector;
import com.ygs.imgproc.util.border.FreichenEdgeDetector;
import com.ygs.imgproc.util.colorspace.RGB;

import java.awt.image.BufferedImage;

import static com.ygs.imgproc.modes.ModeNames.*;

public class BorderMode implements Mode {
    private EdgeDetector detector;

    public BorderMode(String option, BufferedImage bufferedImage) {
        switch (option) {
            case CANNY:
                detector = new CannyEdgeDetecor(bufferedImage);
                break;
            case FREICHEN:
                detector = new FreichenEdgeDetector(bufferedImage);
                break;
        }

    }

    @Override
    public void activate() {
        detector.draw();
    }

    @Override
    public RGB[][] getImage() {
        return detector.getEdges();
    }
}
