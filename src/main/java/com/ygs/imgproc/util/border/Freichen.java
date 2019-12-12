package com.ygs.imgproc.util.border;

public class Freichen {
    private static final double[][] MASK_H = {
            {1, 0, -1},
            {Math.sqrt(2), 0, -Math.sqrt(2)},
            {1, 0, Math.sqrt(-1)}
    };
    private static final double[][] MASK_V = {
            {-1, -Math.sqrt(2), -1},
            {0, 0, 0},
            {1, Math.sqrt(2), 1}
    };

    public static int[][] Horizontal(int[][] raw) {
        int[][] out = null;
        int height = raw.length;
        int width = raw[0].length;

        if (height > 2 && width > 2) {
            out = new int[height - 2][width - 2];

            for (int r = 1; r < height - 1; r++) {
                for (int c = 1; c < width - 1; c++) {
                    int sum = 0;

                    for (int kr = -1; kr < 2; kr++) {
                        for (int kc = -1; kc < 2; kc++) {
                            sum += (MASK_H[kr + 1][kc + 1] * raw[r + kr][c + kc]);
                        }
                    }

                    out[r - 1][c - 1] = (int) (sum * (1 / 2 + Math.sqrt(2))) % 255;
                }
            }
        }

        return out;
    }

    /**
     * Send this method an int[][] array of grayscale pixel values to get a an image resulting
     * from the convolution of this image with the vertical Sobel mask.
     *
     * @param raw int[][], array of grayscale pixel values 0-255
     * @return out  int[][], output array of convolved image.
     */
    public static int[][] Vertical(int[][] raw) {
        int[][] out = null;
        int height = raw.length;
        int width = raw[0].length;

        if (height > 2 || width > 2) {
            out = new int[height - 2][width - 2];

            for (int r = 1; r < height - 1; r++) {
                for (int c = 1; c < width - 1; c++) {
                    int sum = 0;

                    for (int kr = -1; kr < 2; kr++) {
                        for (int kc = -1; kc < 2; kc++) {
                            sum += (MASK_V[kr + 1][kc + 1] * raw[r + kr][c + kc]);
                        }
                    }

                    out[r - 1][c - 1] = (int) (sum * (1 / 2 + Math.sqrt(2))) % 255;
                }
            }
        }

        return out;
    }
}
