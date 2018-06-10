/**
 * Created by Zhenye Na on Jun, 2018
 */

import java.awt.Color;
import java.util.Arrays;

import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

    private Picture pic;
    private double[][] energy;
    private SeamRemover sr;

    public SeamCarver(Picture picture) {
        pic = picture;
        energy = new double[height()][width()];
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                energy[row][col] = energy(col, row);
            }
        }
    }


    // current picture
    public Picture picture() {
        return pic;
    }


    // width of current picture
    public int width() {
        return pic.width();
    }


    // height of current picture
    public int height() {
        return pic.height();
    }


    /**
     * private helper function
     *
     * compute energy along X-direction
     *
     */
    private double energyX(int x, int y) {
        // x stands for column, y stands for row
        // energy along X => column matters

        // a 3-by-4 image (x, y) W by H
        // (0, 0)  	  (1, 0)  	  (2, 0)
        // (0, 1)  	  (1, 1)  	  (2, 1)
        // (0, 2)  	  (1, 2)  	  (2, 2)
        // (0, 3)  	  (1, 3)  	  (2, 3)

        int x1, x2, y1, y2;
        y1 = y;
        y2 = y;


        // pixels in center
        if (x > 0 && x < width() - 1) {
            x1 = x - 1;
            x2 = x + 1;
        } else if (x == 0) {
            x1 = width() - 1;
            x2 = x + 1;
        } else if (x == width() - 1) {
            x1 = x - 1;
            x2 = 0;
        } else {
            throw new IllegalArgumentException("x is out of bounds!");
        }

        Color l = pic.get(x1, y1);
        Color r = pic.get(x2, y2);

        double Rx = Math.abs(l.getRed() - r.getRed());
        double Gx = Math.abs(l.getGreen() - r.getGreen());
        double Bx = Math.abs(l.getBlue() - r.getBlue());

        return Math.pow(Rx, 2) + Math.pow(Gx, 2) + Math.pow(Bx, 2);
    }


    /**
     * private helper function
     *
     * compute energy along Y-direction
     *
     */
    private double energyY(int x, int y) {
        // x stands for column, y stands for row
        // energy along Y => row matters

        // a 3-by-4 image (x, y) W by H
        // (0, 0)  	  (1, 0)  	  (2, 0)
        // (0, 1)  	  (1, 1)  	  (2, 1)
        // (0, 2)  	  (1, 2)  	  (2, 2)
        // (0, 3)  	  (1, 3)  	  (2, 3)

        int x1, x2, y1, y2;
        x1 = x;
        x2 = x;

        if (y > 0 && y < height() - 1) {
            y1 = y - 1;
            y2 = y + 1;
        } else if (y == 0) {
            y1 = height() - 1;
            y2 = y + 1;
        } else if (y == height() - 1) {
            y1 = y - 1;
            y2 = 0;
        } else {
            throw new IllegalArgumentException("y is out of bounds!");
        }

        Color u = pic.get(x1, y1);
        Color d = pic.get(x2, y2);

        double Ry = Math.abs(u.getRed() - d.getRed());
        double Gy = Math.abs(u.getGreen() - d.getGreen());
        double By = Math.abs(u.getBlue() - d.getBlue());

        return Math.pow(Ry, 2) + Math.pow(Gy, 2) + Math.pow(By, 2);
    }


    /**  energy of pixel at column x and row y */
    public double energy(int x, int y) {
        // x stands for column, y stands for row

        // a 3-by-4 image (x, y) W by H
        // (0, 0)  	  (1, 0)  	  (2, 0)
        // (0, 1)  	  (1, 1)  	  (2, 1)
        // (0, 2)  	  (1, 2)  	  (2, 2)
        // (0, 3)  	  (1, 3)  	  (2, 3)

        if (x < 0 || y < 0 || x > width()-1 || y > height()-1) {
            throw new IndexOutOfBoundsException();
        }

        return energyX(x, y) + energyY(x, y);
    }


    /** sequence of indices for horizontal seam */
    public int[] findHorizontalSeam() {
        energy = transpose(energy);
        int[] vSeam = findVerticalSeam();
        energy = transpose(energy);
        return vSeam;
    }


    /**
     *  private helper method
     *
     *  transpose the current image to find horizontal seam
     */
    private double[][] transpose(double[][] original) {

        double[][] transpose = new double[original[0].length][original.length];
        for (int i = 0; i < original.length; i += 1) {
            for (int j = 0; j < original[0].length; j += 1) {
                transpose[j][i] = original[i][j];
            }
        }
        return transpose;
    }


    /**
     *  private helper method
     *
     *  Find smallest element in a 1-D array
     *
     *  @param  e double[] array
     *  @return index of min value in the array
     */
    private int findMin(double[] e) {
        int minIndex = 0;
        for (int i = 0; i < e.length; i += 1) {
            if (e[i] < e[minIndex]) minIndex = i;
        }
        return minIndex;
    }



    /** sequence of indices for vertical seam */
    public int[] findVerticalSeam() {

        int row = energy.length;
        int col = energy[0].length;
        int[] vSeam = new int[row];
        double[] candidate;

        vSeam[0] = findMin(energy[0]);

        for (int index = 1; index < row; index += 1) {
            candidate = new double[col];

            for (int e = 0; e < candidate.length; e += 1) {
                candidate[e] = Integer.MAX_VALUE;
            }

            System.arraycopy(energy[index], vSeam[index - 1] - 1, candidate, vSeam[index - 1] - 1, 3);

            vSeam[index] = findMin(candidate);
        }

        return vSeam;
    }


    /**
     *  private helper method
     *
     *  sanity check of seam
     */
    private boolean checkSeam(int[] seam) {
        int prev = seam[0];
        for (int i = 1; i < seam.length; i += 1) {
            if (seam[i] - prev > 1) {
                return true;
            }
            prev = seam[i];
        }
        return false;

    }


    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width() || !checkSeam(seam)) throw new IllegalArgumentException("wrong length or not a valid seam.");

        pic = sr.removeHorizontalSeam(pic, seam);

        energy = new double[height()][width()];
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                energy[row][col] = energy(col, row);
            }
        }


    }


    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height() || !checkSeam(seam)) throw new IllegalArgumentException("wrong length or not a valid seam.");

        pic = sr.removeVerticalSeam(pic, seam);

        energy = new double[height()][width()];
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                energy[row][col] = energy(col, row);
            }
        }
    }



}
