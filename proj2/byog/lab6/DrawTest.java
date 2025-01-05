package byog.lab6;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class DrawTest {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1000,500);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setScale(0,200);
        StdDraw.point(0, 0);
        StdDraw.point(200, 200);

        Font font = new Font("Times New Roman", Font.BOLD,30);
        StdDraw.setFont(font);
        StdDraw.text(100,100,"Hola Munda");



    }
}
