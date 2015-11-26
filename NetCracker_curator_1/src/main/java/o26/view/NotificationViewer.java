package o26.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.*;

import o26.controller.Journal;
import o26.model.Parameter;
import o26.model.Task;


public class NotificationViewer{

    public void show(Journal journal, int id) {

        String name = ((Task)journal.getTasks().get(id)).getValue(Parameter.TypeParameter.NAME).toString();
        String description = ((Task)journal.getTasks().get(id)).getValue(Parameter.TypeParameter.DESCRIPTION).toString();
        String contacts = ((Task)journal.getTasks().get(id)).getValue(Parameter.TypeParameter.CONTACTS).toString();
        String date = ((GregorianCalendar)(((Task)journal.getTasks().get(id)).getValue(Parameter.TypeParameter.DATE))).getTime().toString();

        JFrame frame = new JFrame();
        frame.setSize(700, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Box boxName = Box.createHorizontalBox();
        JLabel labelName = new JLabel("NAME:");
        JLabel textName = new JLabel(name);
        boxName.add(labelName);
        boxName.add(Box.createHorizontalStrut(12));
        boxName.add(textName);

        Box boxDescription = Box.createHorizontalBox();
        JLabel labelDescription = new JLabel("DESCRIPTION:");
        JLabel textDescription = new JLabel(description);
        boxDescription.add(labelDescription);
        boxDescription.add(Box.createHorizontalStrut(12));
        boxDescription.add(textDescription);

        Box boxContacts = Box.createHorizontalBox();
        JLabel labelContacts = new JLabel("CONTACTS:");
        JLabel textContacts = new JLabel(contacts);
        boxContacts.add(labelContacts);
        boxContacts.add(Box.createHorizontalStrut(12));
        boxContacts.add(textContacts);

        Box boxDate = Box.createHorizontalBox();
        JLabel labelDate = new JLabel("DATE:");
        JLabel textDate = new JLabel(date);
        boxDate.add(labelDate);
        boxDate.add(Box.createHorizontalStrut(12));
        boxDate.add(textDate);

        Box boxPostponed = Box.createHorizontalBox();
        JButton postponed = new JButton("Отложить");
        boxPostponed.add(postponed, Component.CENTER_ALIGNMENT);

        Box boxFrame = Box.createVerticalBox();
        boxFrame.add(Box.createVerticalStrut(12));
        boxFrame.add(boxName);
        boxFrame.add(Box.createVerticalStrut(12));
        boxFrame.add(boxDescription);
        boxFrame.add(Box.createVerticalStrut(12));
        boxFrame.add(boxDate);
        boxFrame.add(Box.createVerticalStrut(12));
        boxFrame.add(boxContacts);
        boxFrame.add(Box.createVerticalStrut(12));
        boxFrame.add(boxPostponed);
        boxFrame.add(Box.createVerticalStrut(12));

        labelDate.setPreferredSize(labelContacts.getPreferredSize());
        labelDescription.setPreferredSize(labelDate.getPreferredSize());
        labelName.setPreferredSize(labelDescription.getPreferredSize());

        frame.setContentPane(boxFrame);
        frame.pack();

        List parameters = new ArrayList<>();
        parameters.add(new Parameter(Parameter.TypeParameter.NAME, name));
        parameters.add(new Parameter(Parameter.TypeParameter.DESCRIPTION, description));
        parameters.add(new Parameter(Parameter.TypeParameter.CONTACTS, contacts));

        postponed.setVisible(true);
        postponed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                holdTask(journal, frame, parameters);      
            }
        }); 
        journal.deleteTask(id);
    }
    
    private void holdTask(Journal journal, JFrame frame, List parameters){
        JFrame postponedFrame = new JFrame("Отложить задачу");
        frame.dispose();
        ArrayList<JTextField> listTextFields = new ArrayList<>();
        Box boxYear = Box.createHorizontalBox();
        JLabel labelYear = new JLabel("Год:");
        JTextField year = new JTextField(15);
        boxYear.add(labelYear);
        boxYear.add(Box.createHorizontalStrut(6));
        boxYear.add(year);
        listTextFields.add(year);

        Box boxMonth = Box.createHorizontalBox();
        JLabel labelMonth = new JLabel("Месяц:");
        JTextField month = new JTextField(15);
        boxMonth.add(labelMonth);
        boxMonth.add(Box.createHorizontalStrut(6));
        boxMonth.add(month);
        listTextFields.add(month);

        Box boxDay = Box.createHorizontalBox();
        JLabel labelDay = new JLabel("День:");
        JTextField day = new JTextField(15);
        boxDay.add(labelDay);
        boxDay.add(Box.createHorizontalStrut(6));
        boxDay.add(day);
        listTextFields.add(day);

        Box boxHour = Box.createHorizontalBox();
        JLabel labelHour = new JLabel("Час:");
        JTextField hour = new JTextField(15);
        boxHour.add(labelHour);
        boxHour.add(Box.createHorizontalStrut(6));
        boxHour.add(hour);
        listTextFields.add(hour);

        Box boxMinute = Box.createHorizontalBox();
        JLabel labelMinute = new JLabel("Минута:");
        JTextField minute = new JTextField(15);
        boxMinute.add(labelMinute);
        boxMinute.add(Box.createHorizontalStrut(6));
        boxMinute.add(minute);
        listTextFields.add(minute);

        Box boxSecond = Box.createHorizontalBox();
        JLabel labelSecond = new JLabel("Секунда:");
        JTextField second = new JTextField(15);
        boxSecond.add(labelSecond);
        boxSecond.add(Box.createHorizontalStrut(6));
        boxSecond.add(second);
        listTextFields.add(second);

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
        postponedFrame.setLocationRelativeTo(null);

        postponedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//                okButton.setSize(20, 20);
        okButton.setVisible(true);
//                okButton.setLocation(400, 150);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputNewDate(journal , parameters, postponedFrame, listTextFields);
            }
        });
    }
    
    private void inputNewDate(Journal journal, List parameters, JFrame postponedFrame,  ArrayList<JTextField> list){
        try {
                int newYear = Integer.parseInt(list.get(0).getText());
                int newMonth = Integer.parseInt(list.get(1).getText());
                int newDay = Integer.parseInt(list.get(2).getText());
                int newHour = Integer.parseInt(list.get(3).getText());
                int newMinute = Integer.parseInt(list.get(4).getText());
                int newSecond = Integer.parseInt(list.get(5).getText());

                GregorianCalendar newDate = new GregorianCalendar(newYear, newMonth - 1, newDay, newHour,
                        newMinute, newSecond);
                parameters.add(new Parameter(Parameter.TypeParameter.DATE, newDate));
                journal.addTask(parameters);
                journal.journalChanged();
                postponedFrame.dispose();

                JFrame frame2 = new JFrame("Отложить задачу");
                frame2.setVisible(true);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame2.setSize(100, 125);
                JLabel successLabel = new JLabel("Задача отложена");
                successLabel.setVisible(true);

                Box success = Box.createHorizontalBox();
                success.add(Box.createVerticalStrut(12));
                success.add(successLabel);
                success.add(Box.createVerticalStrut(12));

                frame2.setContentPane(success);
                frame2.setLocationRelativeTo(null);
        } 
        catch (NumberFormatException e1) {
            JFrame errorFrame = new JFrame("Ошибка");
            JLabel errorLabel = new JLabel("Значения отсутствуют, введен текст или нецелые числа! Исправьте ошибки.");
            Box errorBox = Box.createHorizontalBox();
            errorBox.add(Box.createVerticalStrut(12));
            errorBox.add(errorLabel);
            errorBox.add(Box.createVerticalStrut(12));
            errorFrame.setContentPane(errorBox);
            errorFrame.setSize(400, 250);
            errorFrame.setVisible(true);
            errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
}
