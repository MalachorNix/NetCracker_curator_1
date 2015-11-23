package o26.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;

import o26.controller.Journal;
import o26.model.Task;
import o26.model.TaskParameter;


public class NotificationViewer{

    public void show(Journal journal, int id) {
        String NAME = ((Task)journal.getTasks().get(id)).getValue(TaskParameter.NAME).toString();
        String DESCRIPTION = ((Task)journal.getTasks().get(id)).getValue(TaskParameter.DESCRIPTION).toString();
        String CONTACTS = ((Task)journal.getTasks().get(id)).getValue(TaskParameter.CONTACTS).toString();
        String DATE = ((GregorianCalendar)(((Task)journal.getTasks().get(id)).getValue(TaskParameter.DATE))).getTime().toString();
      
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

        Map<TaskParameter, Object> map = new HashMap<>();
        map.put(TaskParameter.NAME, NAME);
        map.put(TaskParameter.DESCRIPTION, DESCRIPTION);
//        map.put(TaskParameter.DATE, DATE);
        map.put(TaskParameter.CONTACTS, CONTACTS);
//        Task cloneTask = new Task(map);

        JButton postponed = new JButton("Отложить");
        postponed.setVisible(true);
        postponed.setLocation(21, 21);
        postponed.setSize(150, 150);
        postponed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                * Здесь сделать ввод даты.
                * */
                GregorianCalendar calendar = new GregorianCalendar(2015, 11, 25, 14, 30);
                map.put(TaskParameter.DATE, calendar);
                journal.addTask(map);
                System.out.println("Отложил");
            }
        });
        frame.getContentPane().add(postponed);

        /*JButton complete = new JButton("Завершить");
        complete.setVisible(true);
        complete.setLocation(322, 322);
        complete.setSize(150, 150);
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                journal.deleteTask(id);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        frame.getContentPane().add(complete);*/

        
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
