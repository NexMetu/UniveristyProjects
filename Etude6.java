/*
  Matthew Lang
  2018313
  Etude 6
*/

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Etude6 extends JFrame{

    static int genNum;
    static Double ratio;
    static int lineLength = 50;
    static int size = 700;
    
    public static void main(String[]args){
        JPanel jp = new JPanel();
        if(args.length >= 1){
            if(Character.isDigit(args[0].charAt(0)))
                genNum = Integer.parseInt(args[0]);
            if(args.length == 2){
                ratio = Double.parseDouble(args[1]);
            }
        }else{
            genNum = 10;
            ratio = 1.0;    			
        			
        }
        Etude6 x = new Etude6();
        x.getContentPane().add(jp);
        x.setSize(size,size);
        x.setDefaultCloseOperation(EXIT_ON_CLOSE);
        x.setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        Point start = new Point(size/2,size/2);
        Lines(g,start,false,genNum,lineLength);
    }

    private void Lines(Graphics g, Point x, boolean flip,
                       int count, int linelength){
        Point x1;
        Point x2;
        int lL = linelength;
        if(count < 0) return;
        if(!flip){
            g.drawLine((x.x-lL/2),(x.y),(x.x+lL/2),(x.y));
            x1 = new Point(x.x-lL/2,x.y);
            x2 = new Point(x.x+lL/2,x.y);
        }else{
            g.drawLine((x.x),(x.y-lL/2),(x.x),(x.y+lL/2));
            x1 = new Point(x.x,x.y-lL/2);
            x2 = new Point(x.x,x.y+lL/2);
        }
        flip = !flip;
        double rL = lL * ratio;
        lL = (int) rL;
        Lines(g,x1,flip,count-1,lL);
        Lines(g,x2,flip,count-1,lL);
    }

}
        
        
        
        





