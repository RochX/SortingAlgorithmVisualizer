import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

//this class handles the drawing of the bars to visualize the array
public class Draw extends JComponent {

    int[] arr;
    ArrayList<Integer> changed;
    int maxValue;
    String text;
    public Draw(int[] arr)
    {
        this.arr = arr;
        maxValue = getMax(arr);
        changed = new ArrayList<>();
        text = "Use the selection box to run a sorting algorithm.";
    }

    public void setArr(int[] arr) {
        this.arr = arr;
        maxValue = getMax(arr);
    }

    public void setText(String str) {
        text = str;
    }

    public void paintComponent(Graphics g) {
        //setup with background
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(Color.WHITE);

        //get sizing based on the max value of the array
        double spacingX = 1, spacingY = 50;
        double x = 0, w = (double) getWidth() / arr.length;
        double scale = (getHeight()-spacingY) / maxValue;

        g2.setColor(Color.WHITE);
        Font font = new Font("Dialog", Font.PLAIN, 20);
        g2.setFont(font);
        g2.drawString(text, 5, 25);

        //draw each rectangle
        for (int i = 0; i < arr.length; i++) {
            //get the height and scale it up
            double h = arr[i];
            h *= scale;

            //check if the current position was recently changed and color it red if so
            if (changed.contains(i))
                g2.setColor(Color.RED);
            else
                g2.setColor(Color.WHITE);

            //draw a rectangle
            Rectangle2D.Double rect = new Rectangle2D.Double(x,getHeight()-h,w-spacingX, h);
            g2.fill(rect);

            //move on to next rectangle
            x += w;
        }
    }

    private int getMax(int[] arr) {
        int max = 0;
        for (int a : arr) {
            if (max < a)
                max = a;
        }

        return max;
    }
}