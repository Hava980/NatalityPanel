import java.awt.*;
import java.util.ArrayList;

public class Column extends Chart{

    public Column(ArrayList<Double> data, ArrayList<String> labels) {
        super(data,labels);//Initialise.
    }

    public void draw(Graphics g, int x, int y, int width, int height) {

        g.drawRect(x,y,width,height);
        double max=data.get(0);
        int numOfLine=10;//Arbitrary number of lines.
        int heightOfLine,widthOfRect;
        for (int i=0;i<data.size();i++) {//Finds the max number.
            if(data.get(i)>max)
                max=data.get(i);
        }

        heightOfLine=(height-40)/numOfLine;//Lower from the height slightly so that the lines inside the square
        // and divide by the number of lines.
        int jump;
        if(max>=10) {
            jump = (int) (max / numOfLine);//We will create equal jump between each line until we reach the maximum.
        }
        else {
            jump=1;
        }

        widthOfRect=(width+10)/(2*(data.size()+1));//Each rectangle will have the same width,
        // and between it to another rectangle will be the same distance.
        int heightOfStart=y+height-20;
        int jumps=0;

        for(int i=0;i<=numOfLine;i++){//Draw lines, string and number of height of line.
            String str=""+jumps;
            g.drawLine(x+30,heightOfStart,x+(width-20),heightOfStart);
            g.drawString(str,x,heightOfStart);
            heightOfStart-=heightOfLine;
            jumps+=jump;

        }
        jumps-=jump;
        heightOfStart=y+height-20;
        int locationOfRect=x+20+widthOfRect;
        for (int i=0;i<data.size();i++){//Draw rectangle and labels.
            int heightOfRect=(int)(heightOfStart-((data.get(i)/jumps)*(height-40)));
            g.setColor(Color.BLUE);
            g.fillRect(locationOfRect+widthOfRect*2*i,heightOfRect,
                    width/(2*(data.size()+1)),(int)((data.get(i)/jumps)*(height-40)));
            g.setColor(Color.BLACK);
            g.drawString(labels.get(i),locationOfRect-5+widthOfRect*2*i,height+y-10);

        }

    }
}
