import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pie extends Chart{


    public Pie(ArrayList<Double> data, ArrayList<String> labels) {
        super(data, labels);
    }
    private Color randomColor(){//Creates random color.
        return new Color( (int)(Math.round(Math.random()*255)) ,
                (int)(Math.round(Math.random()*255)),(int)(Math.round(Math.random()*255)));
    }

    public void draw(Graphics g, int x, int y, int width, int height){
        double sum=0,sizeOfString=0;
        int startOfAngle=0;

        for (Double datum : data) {//sum=The sum of all the data.
            sum += datum;
        }

        for(int i=0;i < data.size();i++) {
            sizeOfString+=(2*x+width)/(labels.size()+1);//Creates relative size foe each label.
            int sizeOfAngle=(int)(((data.get(i))/sum)*360);//Calculates the size of the angel.
            g.setColor(randomColor());
            g.fillArc(x,y,width,height,startOfAngle,sizeOfAngle);//Creates an angel of appropriate size
            // relative to the input.
            g.fillRect((int)(sizeOfString) -10,y+height+45,5,5);
            //Creates small square next to the labels.
            if(i==data.size()-1)//After the last angel, complete the angel to 360.
                g.fillArc(x,y,width,height,startOfAngle,360-startOfAngle);
            g.setColor(Color.BLACK);
            g.drawString(labels.get(i),(int)(sizeOfString),y+height+50);//Creates labels under the pie.
            startOfAngle+=sizeOfAngle;
        }
    }
}
