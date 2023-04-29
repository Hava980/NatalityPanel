import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class NatalityPanel extends JPanel {
    private JComboBox box;
    private JButton clear;
    private JButton showChart;
    public NatalityPanel(){//Initialise the buttons, adds them to the frame and connects them to the listener.
        clear=new JButton("Clear");
        showChart=new JButton("Show Chart");
        box=new JComboBox();
        box.addItem("");
        box.addItem("Gender");
        box.addItem("Months of birth");

        clear.addActionListener(new ButtonListener());
        showChart.addActionListener(new ButtonListener());

        add(showChart);
        add(clear);
        add(box);
    }
    private ArrayList<Double> readDataD(File f,boolean b) {//Read data from "NatalityMini".

        ArrayList<Double> amountOfMAndF=new ArrayList<>();
        double[] amountOfBirth=new double[12];
        double amountF=0,amountM=0;

        try
        {
            FileReader file=new FileReader(f);
            BufferedReader bR=new BufferedReader(file);
            while (true){
                String gender =bR.readLine();//Read lines.
                if (gender==null) {//The loop stops when we reach the end of the lines.
                    //Adds to the array the amount of males and females.
                    amountOfMAndF.add(amountM);
                    amountOfMAndF.add(amountF);

                    break;
                }
                int month= Integer.parseInt(gender.substring(5,7));
                //Counts how many babies were borne each month.
                amountOfBirth[month-1]++;

                if(gender.charAt(8) == 'M')//Counts males and females.
                    amountM++;
                else
                    amountF++;
            }
        } catch (FileNotFoundException e1) {
            System.out.println("file not found.");
        }
        catch (IOException e){
            System.out.println("can't read one of the lines.");
        }

        if(b){//Returns list of amount of births in each month.
            ArrayList<Double> amountOfB=new ArrayList<>();
            for (Double dob: amountOfBirth){
                amountOfB.add(dob);
            }
            return amountOfB;
        }//Returns list of amount of males and females.
        return amountOfMAndF;

    }


    private void paintPieOrColumn(ArrayList<Double> listD, ArrayList<String> listS, boolean b){
        if(b) {//Displays on the panel a chart of sticks that will show each month the number of births that were in it.
            Column column=new Column(listD,listS);
            column.draw(getGraphics(), 100, 100, 750, 300);
        }
        else {//Display a pie chart on the panel that shows the number of births of males compared to females.
            Pie p = new Pie(listD, listS);
            p.draw(getGraphics(), 200, 100, 300, 300);
        }

    }
    private void clear(){//Clears the pie/column.
        getGraphics().clearRect(50,50,900,900);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            File file = new File("NatalityMini.csv");
            if(e.getSource()==clear)//If we clicked on "clear".
                clear();
            else if(e.getSource() == showChart && box.getSelectedIndex()==1){//If we clicked on "showChart" and "Gender"
                clear();
                ArrayList<String> listS = new ArrayList<>();
                listS.add("Male");
                listS.add("Female");
                ArrayList<Double> listD = readDataD(file, false);
                paintPieOrColumn(listD, listS,false);
            }
            else if(e.getSource() == showChart && box.getSelectedIndex()==2) {
                //If we clicked on "showChart" and "Months of birth"
                clear();
                ArrayList<String> listS = new ArrayList<>();
                listS.add("January");
                listS.add("February");
                listS.add("March");
                listS.add("April");
                listS.add("May");
                listS.add("June");
                listS.add("July");
                listS.add("August");
                listS.add("September");
                listS.add(" October");
                listS.add("November");
                listS.add("December");
                ArrayList<Double> listD = readDataD(file, true);
                paintPieOrColumn(listD, listS,true);
            }
        }
    }
}
