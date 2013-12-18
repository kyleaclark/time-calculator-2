/*
	Kyle Clark
	09/30/04
	file searcher new version 1.3: now uses exact name as entered
	Searches files in current directory
	and creates a folder with the entered string
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.text.*;
import java.beans.*;

public class Data_Sorter implements ActionListener //implements PropertyChangeListener
{

	JFrame converterFrame ;
	JPanel mainPanel;
	JFormattedTextField hrs, mins, secs;
	JLabel hrs_label, mins_label, secs_label;
    JButton enter_data, calc_data;

    int phours, pminutes, pseconds;
    double total_time;
    int total_hours, total_minutes, total_seconds;

    private int hours = 0;
	private int minutes = 0;
    private int seconds = 0;

    //Formats to format and parse numbers
	//private NumberFormat hrs_format;
	//public NumberFormat mins_format;
    //public NumberFormat secs_format;


	public  Data_Sorter()
	  {

		converterFrame = new JFrame("Calculate Time Inputs to Total Time");
        converterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        converterFrame.setSize(new Dimension(960, 80));

        //Create and set up the panel.
        mainPanel = new JPanel(new GridLayout(4, 2));

	    add_widgets();

		converterFrame.getContentPane().add(mainPanel);

	  }
	///////////////////////////////////////////////////////////

/*	protected MaskFormatter createFormatter(String s)
	  {
					    MaskFormatter formatter = null;
					    try {
					        formatter = new MaskFormatter(s);
                            //value_formatter = new valueOf(formatter);
					    } catch (java.text.ParseException exc) {
					        System.err.println("formatter is bad: " + exc.getMessage());
					        System.exit(-1);
					    }
					    return formatter;
      }   */

	public void add_widgets() {
	        //Create widgets.

	       // hrs = new JFormattedTextField(createFormatter(new Integer(hours)));
	        hrs = new JFormattedTextField();
			hrs.setValue(new Integer(hours));
			hrs.setColumns(10);
            hrs.addPropertyChangeListener("value", this);


	        hrs_label = new JLabel("Hours", SwingConstants.LEFT);

	        mins = new JFormattedTextField();
			mins.setValue(new Integer(minutes));
			mins.setColumns(10);
            mins.addPropertyChangeListener("value", this);

	      //  mins = new JFormattedTextField(createFormatter(new Integer(minutes)));
	        mins_label = new JLabel("Minutes", SwingConstants.LEFT);

	        secs = new JFormattedTextField();
			secs.setValue(new Integer(seconds));
			secs.setColumns(10);
            secs.addPropertyChangeListener("value", this);

	      //  secs = new JFormattedTextField(createFormatter(new Integer(seconds)));
	        secs_label = new JLabel("Seconds", SwingConstants.LEFT);

	        enter_data = new JButton("Enter Time");
            calc_data = new JButton("Calculate Data");

	        //Listen to events from the Convert button.
	        enter_data.addActionListener(this);
	        calc_data.addActionListener(new calc_total());

	        //Add the widgets to the container.
	        mainPanel.add(hrs);
	        mainPanel.add(hrs_label);
	        mainPanel.add(mins);
	        mainPanel.add(mins_label);
	        mainPanel.add(secs);
	        mainPanel.add(secs_label);

	       // mainPanel.add(new JSeparator(JSeparator.HORIZONTAL));

	        mainPanel.add(enter_data);
	        mainPanel.add(calc_data);

	        hrs_label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	        mins_label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }


	public void display()
	   {
	      converterFrame.pack();
	      converterFrame.show();

	   }

   public void propertyChange(PropertyChangeEvent e) {
           Object source = e.getSource();
           if (source == hrs) {
               hours = ((Number)hrs.getValue()).intValue();
           } else if (source == mins) {
               minutes = ((Number)mins.getValue()).intValue();
           } else if (source == secs) {
               seconds = ((Number)secs.getValue()).intValue();
           }

           //double payment = computePayment(amount, rate, numPeriods);
          // paymentField.setValue(new Double(payment));
    }


 class calc_total implements ActionListener
   {
     public void actionPerformed(ActionEvent e)
	  {
        //Parse degrees Celsius as a double and convert to Fahrenheit.
		//phours = phours + (int)Double.parseDouble(hrs.getText());
		//pminutes = pminutes + (int)Double.parseDouble(mins.getText());
		//pseconds = pseconds + (int)Double.parseDouble(secs.getText());


        phours = phours + hours;
        pminutes = pminutes + minutes;
        pseconds = pseconds + seconds;

		total_time = (double)(phours) + (((double)pminutes) / 60.0) + (((double)pseconds) / 3600.0);
		//total_time = (double)3 / 10;
		//secs_label.setText(total_time + " seconds");
		    //this takes the whole nubmer of hours
		    //it takes minutes as it is a 1/60 ratio of hours
		    //seconds is a 1/3600 ratio of hours bc it is a 1/60 ratio of minutes
		    //adds them all up as one number that is a ratio

		total_hours = (int)total_time;
		    //taking the whole nubmer value of total_time

		total_time = total_time - total_hours;
		    //it becomes the original total time minus the whole number value of hours
		    //hrs_label.setText(total_time + " time");

		total_minutes = (int)(total_time * 60.0);
		    //with the ratio of minutes to hours being 1/60 it gives the whole number
		      //value of minutes in regards to its percentage of hours

		total_time = ((total_time * 60.0) - total_minutes) + .001;
		    //to subtract the whole number value you of total minutes you must convert
		      //the ratio of total time as well.  since seconds is a 1/60 ratio of
		      //minutes it is being multiplied by 60.  the +.001 is to help with the
		      //loss of values due to typecasting to short values

		total_seconds = (int)(total_time * 60.0);
		    //with the ratio of seconds to minutes being 1/60 it gives the whole number
             //value of seconds in regards to its percentage of minutes


		hrs_label.setText(total_hours + "<html>hours</html>");
		mins_label.setText(total_minutes + " minutes");
		secs_label.setText(total_seconds + " seconds");


           }
    }



	public void actionPerformed(ActionEvent event)
	  {
        //Parse degrees Celsius as a double and convert to Fahrenheit.
        //phours = phours + (int)Double.parseDouble(hrs.getText());
		//pminutes = pminutes + (int)Double.parseDouble(mins.getText());
        //pseconds = pseconds + (int)Double.parseDouble(secs.getText());

        phours = phours + hours;
		pminutes = pminutes + minutes;
        pseconds = pseconds + seconds;

        //int total = (int)Double.parseDouble(hrs.getText());

        hrs_label.setText(phours + " hours");
        mins_label.setText(pminutes + " minutes");
        secs_label.setText(pseconds + " seconds");

      }



 }
	///////////////////////////////////////////////////////////////

