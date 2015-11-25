package o26.view;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import o26.controller.Journal;
import o26.model.Task;
import o26.model.TaskParameter;


public class NotificationViewer{

    public void show(Journal journal, int id) {

        String name = ((Task)journal.getTasks().get(id)).getValue(TaskParameter.NAME).toString();
        String description = ((Task)journal.getTasks().get(id)).getValue(TaskParameter.DESCRIPTION).toString();
        String contacts = ((Task)journal.getTasks().get(id)).getValue(TaskParameter.CONTACTS).toString();
        String date = ((GregorianCalendar)(((Task)journal.getTasks().get(id)).getValue(TaskParameter.DATE))).getTime().toString();
      
        String header = "<html>"+name+"<br>"+date;
        JLabel headingLabel = new JLabel(header);
        
        String message = "<html>"+description+"<br>"+contacts;
        JLabel messageLabel = new JLabel(message);

        JFrame frame = new JFrame();
        frame.setSize(600, 250);
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
        
        Map<TaskParameter, Object> map = new HashMap<>();
        map.put(TaskParameter.NAME, name);
        map.put(TaskParameter.DESCRIPTION, description);
        map.put(TaskParameter.CONTACTS, contacts);

        JButton postponed = new JButton("Отложить");
        postponed.setVisible(true);
        postponed.setLocation(21, 21);
        postponed.setSize(150, 150);
        postponed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame1 = new JFrame("Отложить задачу");
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                frame1.getContentPane().add(panel);

                JTextField year = new JTextField("Год");
                JTextField month = new JTextField("Месяц");
                JTextField day = new JTextField("День");
                JTextField hour = new JTextField("Час");
                JTextField minute = new JTextField("Минута");
                JTextField second = new JTextField("Секунда");

                year.setVisible(true);
                month.setVisible(true);
                day.setVisible(true);
                hour.setVisible(true);
                minute.setVisible(true);
                second.setVisible(true);
                frame1.setVisible(true);

                frame1.setSize(600, 250);

                panel.add(year);
                panel.add(month);
                panel.add(day);
                panel.add(hour);
                panel.add(minute);
                panel.add(second);

                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JButton button = new JButton("ОК");
                button.setSize(20, 20);
                button.setVisible(true);
                button.setLocation(400, 150);
                panel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int newYear = Integer.parseInt(year.getText());
                        int newMonth = Integer.parseInt(month.getText());
                        int newDay = Integer.parseInt(day.getText());
                        int newHour = Integer.parseInt(hour.getText());
                        int newMinute = Integer.parseInt(minute.getText());
                        int newSecond = Integer.parseInt(second.getText());
                        GregorianCalendar newDate = new GregorianCalendar(newYear, newMonth - 1, newDay, newHour, newMinute, newSecond);
                        map.put(TaskParameter.DATE, newDate);
                        journal.addTask(map);
                        journal.journalChanged();
                    }
                });
            }
        });
        frame.getContentPane().add(postponed);

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
