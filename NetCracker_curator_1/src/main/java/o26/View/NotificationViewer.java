package o26.View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.GregorianCalendar;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import o26.Controller.Journal;
import o26.Model.Task;
import o26.Model.TaskParameters;


public class NotificationViewer{

    public void show(Journal journal, int id) {
        String NAME = ((Task)journal.getTasks().get(id)).getValue(TaskParameters.NAME).toString();
        String DESCRIPTION = ((Task)journal.getTasks().get(id)).getValue(TaskParameters.DESCRIPTION).toString();
        String CONTACTS = ((Task)journal.getTasks().get(id)).getValue(TaskParameters.CONTACTS).toString();
        String DATE = ((GregorianCalendar)(((Task)journal.getTasks().get(id)).getValue(TaskParameters.DATE))).getTime().toString();
      
        String header = "<html>"+NAME+"<br>"+DATE;
        JLabel headingLabel = new JLabel(header);
        
        String message = "<html>"+DESCRIPTION+"<br>"+CONTACTS;
        JLabel messageLabel = new JLabel(message);
        /*
        JButton closeButton = new JButton("X");
        closeButton.setMargin(new Insets(1, 4, 1, 4));
        closeButton.setFocusable(false);
        */
        JFrame frame = new JFrame();
        frame.setSize(600,250);
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        headingLabel.setOpaque(false);
        
        frame.add(headingLabel, constraints);
        
        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridx = 0;
        
        //frame.add(closeButton, constraints);
        
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        
        frame.add(messageLabel, constraints);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        journal.deleteTask(id);
    }
}
