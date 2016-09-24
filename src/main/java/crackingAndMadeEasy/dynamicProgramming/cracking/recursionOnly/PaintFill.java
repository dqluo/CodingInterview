package crackingAndMadeEasy.dynamicProgramming.cracking.recursionOnly;

import util.ArrayUtil;

/**
 * User: xinyuwan, Date: 12/5/13, Time: 8:39 PM
 */
public class PaintFill
{
    /*
     *  Problem: Implement a "paint fill" function that given a
     *  screen(matrix) of colors, a point, and a new color, fill in
     *  the surrounding area until the color changes from the original color.
     */
    public static void main(String[] args)
    {
        Color[][] screen={{Color.Red, Color.Red, Color.Yellow},
                {Color.Black, Color.Red, Color.Yellow}, {Color.Black, Color.Red, Color.Yellow}};
        ArrayUtil.printMatrix(screen);
        System.out.println("Now painting from [1, 1]...");
        paint(screen, 1, 1, Color.Green);
        System.out.println("After painting: ");
        ArrayUtil.printMatrix(screen);


    }

    enum Color{Black, White, Red, Yellow, Green}
    public static boolean paint(Color[][] screen, int x, int y, Color ncolor)
    {
        return paint(screen, x, y, screen[y][x], ncolor);
    }
    private static boolean paint(Color[][] screen, int x, int y, Color ocolor, Color ncolor)
    {
        if(x < 0 || y< 0 || y >= screen.length || x >= screen[0].length)
            return false;
        if(screen[y][x] == ocolor)
        {
            screen[y][x]=ncolor;
            paint(screen, x-1, y, ocolor, ncolor);
            paint(screen, x+1, y, ocolor, ncolor);
            paint(screen, x, y-1, ocolor, ncolor);
            paint(screen, x, y+1, ocolor, ncolor);
        }
        return true;
    }
}
