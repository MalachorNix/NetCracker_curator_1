package o26.view;

import java.awt.*;
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

                JFrame postponedFrame = new JFrame("Отложить задачу");

                Box boxYear = Box.createHorizontalBox();
                JLabel labelYear = new JLabel("Год:");
                JTextField year = new JTextField(15);
                boxYear.add(labelYear);
                boxYear.add(Box.createHorizontalStrut(6));
                boxYear.add(year);

                Box boxMonth = Box.createHorizontalBox();
                JLabel labelMonth = new JLabel("Месяц:");
                JTextField month = new JTextField(15);
                boxMonth.add(labelMonth);
                boxMonth.add(Box.createHorizontalStrut(6));
                boxMonth.add(month);

                Box boxDay = Box.createHorizontalBox();
                JLabel labelDay = new JLabel("День:");
                JTextField day = new JTextField(15);
                boxDay.add(labelDay);
                boxDay.add(Box.createHorizontalStrut(6));
                boxDay.add(day);

                Box boxHour = Box.createHorizontalBox();
                JLabel labelHour = new JLabel("Час:");
                JTextField hour = new JTextField(15);
                boxHour.add(labelHour);
                boxHour.add(Box.createHorizontalStrut(6));
                boxHour.add(hour);

                Box boxMinute = Box.createHorizontalBox();
                JLabel labelMinute = new JLabel("Минута:");
                JTextField minute = new JTextField(15);
                boxMinute.add(labelMinute);
                boxMinute.add(Box.createHorizontalStrut(6));
                boxMinute.add(minute);

                Box boxSecond = Box.createHorizontalBox();
                JLabel labelSecond = new JLabel("Секунда:");
                JTextField second = new JTextField(15);
                boxSecond.add(labelSecond);
                boxSecond.add(Box.createHorizontalStrut(6));
                boxSecond.add(second);

                Box boxOK = Box.createHorizontalBox();
                JButton okButton = new JButton("ОК");
                boxOK.add(okButton, Component.CENTER_ALIGNMENT);

                labelMinute.setPreferredSize(labelSecond.getPreferredSize());
                labelHour.setPreferredSize(labelMinute.getPreferredSize());
                labelDay.setPreferredSize(labelHour.getPreferredSize());
                labelMonth.setPreferredSize(labelDay.getPreferredSize());
                labelYear.setPreferredSize(labelMonth.getPreferredSize());

                Box mainBox = Box.createVerticalBox();
                mainBox.add(Box.createVerticalStrut(12));
                mainBox.add(boxYear);
                mainBox.add(Box.createVerticalStrut(12));
                mainBox.add(boxMonth);
                mainBox.add(Box.createVerticalStrut(12));
                mainBox.add(boxDay);
                mainBox.add(Box.createVerticalStrut(12));
                mainBox.add(boxHour);
                mainBox.add(Box.createVerticalStrut(12));
                mainBox.add(boxMinute);
                mainBox.add(Box.createVerticalStrut(12));
                mainBox.add(boxSecond);
                mainBox.add(Box.createVerticalStrut(17));
                mainBox.add(boxOK);

                postponedFrame.setContentPane(mainBox);
                postponedFrame.setResizable(false);
                postponedFrame.pack();



                year.setVisible(true);
                month.setVisible(true);
                day.setVisible(true);
                hour.setVisible(true);
                minute.setVisible(true);
                second.setVisible(true);
                postponedFrame.setVisible(true);

                postponedFrame.setSize(200, 300);



                postponedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//                okButton.setSize(20, 20);
                okButton.setVisible(true);
//                okButton.setLocation(400, 150);
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int newYear = Integer.parseInt(year.getText());
                        int newMonth = Integer.parseInt(month.getText());
                        int newDay = Integer.parseInt(day.getText());
                        int newHour = Integer.parseInt(hour.getText());
                        int newMinute = Integer.parseInt(minute.getText());
                        int newSecond = Integer.parseInt(second.getText());
                        GregorianCalendar newDate = new GregorianCalendar(newYear, newMonth - 1, newDay, newHour,
                                newMinute, newSecond);
                        map.put(TaskParameter.DATE, newDate);
                        journal.addTask(map);
                        journal.journalChanged();
                        postponedFrame.dispose();

                        JFrame frame2 = new JFrame("Отложить задачу");
                        frame2.setVisible(true);
                        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame2.setSize(200, 250);
                        JTextArea textArea = new JTextArea("Задача отложена");
                        textArea.setVisible(true);
                        frame2.getContentPane().add(textArea);
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
