package o26.view;

import o26.controller.Journal;
import o26.model.Parameter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


public class NotificationViewer {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM y 'г.' HH:mm:ss", new Locale("ru"));

    public void show(Journal journal, int id) {

        if (!journal.getTasks().isEmpty()) {
            String name = journal.getTasks().get(id).getValue(Parameter.TypeParameter.NAME).toString();
            String description = journal.getTasks().get(id).getValue(Parameter.TypeParameter.DESCRIPTION).toString();
            String contacts = journal.getTasks().get(id).getValue(Parameter.TypeParameter.CONTACTS).toString();
            String date = simpleDateFormat.format(((GregorianCalendar) (journal.getTasks().get(id).getValue(Parameter.TypeParameter.DATE))).getTime());

            List<Parameter> parameters = new ArrayList<>();
            parameters.add(new Parameter(Parameter.TypeParameter.NAME, name));
            parameters.add(new Parameter(Parameter.TypeParameter.DESCRIPTION, description));
            parameters.add(new Parameter(Parameter.TypeParameter.CONTACTS, contacts));

            JFrame frame = new JFrame();
            frame.setSize(800, 250);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    holdTask(journal, frame, parameters);
                }
            });
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JLabel labelName = new JLabel("Название задачи:");
            Box boxName = createParameterBox(labelName, name, 12);

            JLabel labelDescription = new JLabel("Описание задачи:");
            Box boxDescription = createParameterBox(labelDescription, description, 12);

            JLabel labelContacts = new JLabel("Контакты:");
            Box boxContacts = createParameterBox(labelContacts, contacts, 12);

            JLabel labelDate = new JLabel("Время:");
            Box boxDate = createParameterBox(labelDate, date, 12);

            Box boxButtons = Box.createHorizontalBox();
            JButton postponed = new JButton("Отложить");
            JButton complete = new JButton("Завершить");
            boxButtons.add(postponed, Component.CENTER_ALIGNMENT);
            boxButtons.add(Box.createHorizontalStrut(12));
            boxButtons.add(complete, Component.CENTER_ALIGNMENT);

            Box boxFrame = Box.createVerticalBox();
            Box[] boxes = {boxName, boxDescription, boxDate, boxContacts, boxButtons};
            boxFrame.add(Box.createVerticalStrut(12));

            for (Box box : boxes) {
                boxFrame.add(box);
                boxFrame.add(Box.createVerticalStrut(12));
            }

            labelDate.setPreferredSize(labelContacts.getPreferredSize());
            labelDescription.setPreferredSize(labelDate.getPreferredSize());
            labelName.setPreferredSize(labelDescription.getPreferredSize());

            frame.setContentPane(boxFrame);
            frame.pack();
            frame.setResizable(false);

            postponed.setVisible(true);
            postponed.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    holdTask(journal, frame, parameters);
                }
            });
            journal.deleteTask(id);

            complete.setVisible(true);
            complete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    JFrame completeFrame = new JFrame("Завершено");
                    completeFrame.setVisible(true);
                    completeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    completeFrame.setResizable(false);
                    completeFrame.setSize(100, 125);

                    JLabel completeLabel = new JLabel("Задача завершена");
                    completeLabel.setVisible(true);

                    Box completeBox = Box.createHorizontalBox();
                    completeBox.add(Box.createVerticalStrut(12));
                    completeBox.add(completeLabel);
                    completeBox.add(Box.createVerticalStrut(12));

                    completeFrame.setContentPane(completeBox);
                    completeFrame.setLocationRelativeTo(null);
                    completeFrame.setResizable(false);


                }
            });
        }
    }

    private void holdTask(Journal journal, JFrame frame, List<Parameter> parameters) {
        JFrame postponedFrame = new JFrame("Отложить задачу");
        frame.dispose();
        ArrayList<JTextField> listTextFields = new ArrayList<>();

        JLabel labelYear = new JLabel("Год:");
        Box boxYear = createCalendarBox(15, 6, listTextFields, labelYear);

        JLabel labelMonth = new JLabel("Месяц:");
        Box boxMonth = createCalendarBox(15, 6, listTextFields, labelMonth);

        JLabel labelDay = new JLabel("День:");
        Box boxDay = createCalendarBox(15, 6, listTextFields, labelDay);

        JLabel labelHour = new JLabel("Час:");
        Box boxHour = createCalendarBox(15, 6, listTextFields, labelHour);

        JLabel labelMinute = new JLabel("Минута:");
        Box boxMinute = createCalendarBox(15, 6, listTextFields, labelMinute);

        JLabel labelSecond = new JLabel("Секунда:");
        Box boxSecond = createCalendarBox(15, 6, listTextFields, labelSecond);

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
        postponedFrame.setVisible(true);

        postponedFrame.setSize(200, 300);
        postponedFrame.setLocationRelativeTo(null);

        postponedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        okButton.setVisible(true);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputNewDate(journal, parameters, postponedFrame, listTextFields);
            }
        });
    }

    private void inputNewDate(Journal journal, List<Parameter> parameters, JFrame postponedFrame, List<JTextField> list) {
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
            journal.addTask(parameters, journal.getTasks());
            journal.journalChanged();
            postponedFrame.dispose();

            JFrame frame2 = new JFrame("Отложить задачу");
            frame2.setVisible(true);
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setSize(322, 125);
            String newDateFormat = simpleDateFormat.format(newDate.getTime());
            JLabel successLabel = new JLabel("Задача отложена на ");
            JLabel successLabelDate = new JLabel(newDateFormat);
            successLabel.setVisible(true);
            successLabelDate.setVisible(true);

            successLabel.setPreferredSize(successLabelDate.getPreferredSize());

            Box success = Box.createHorizontalBox();
            success.add(Box.createVerticalStrut(12));
            success.add(successLabel);
            success.add(Box.createVerticalStrut(12));
            success.add(successLabelDate);
            success.add(Box.createVerticalStrut(12));

            frame2.setContentPane(success);
            frame2.setLocationRelativeTo(null);
            frame2.setResizable(false);
        } catch (NumberFormatException e1) {
            JFrame errorFrame = new JFrame("Ошибка");
            JLabel errorLabel = new JLabel("Значения отсутствуют, введен текст или нецелые числа! Исправьте ошибки.");
            Box errorBox = Box.createHorizontalBox();
            errorBox.add(Box.createVerticalStrut(12));
            errorBox.add(errorLabel);
            errorBox.add(Box.createVerticalStrut(12));
            errorFrame.setContentPane(errorBox);
            errorFrame.setSize(500, 250);
            errorFrame.setVisible(true);
            errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            errorFrame.setResizable(false);
        }
    }

    private Box createCalendarBox(int textFieldColumns, int strutWidth, List<JTextField> listTextFields, JLabel label) {

        Box box = Box.createHorizontalBox();
        JTextField textField = new JTextField(textFieldColumns);

        box.add(label);
        box.add(Box.createHorizontalStrut(strutWidth));
        box.add(textField);
        listTextFields.add(textField);

        textField.setVisible(true);

        return box;
    }

    private Box createParameterBox(JLabel descriptionLabel, String text, int widthStrut) {
        Box box = Box.createHorizontalBox();
        JLabel data = new JLabel(text);
        box.add(descriptionLabel);
        box.add(Box.createHorizontalStrut(widthStrut));
        box.add(data);
        return box;
    }
}
