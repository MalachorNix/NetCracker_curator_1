package o26.view;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Меню";

    @Override
    public void show(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        List<AbstractMenuItem> item = new ArrayList<>();
        item.add(new ListMenuItem());
        item.add(new AddMenuItem());
        item.add(new EditMenuItem());
        item.add(new DeleteMenuItem());
        int select;

        try {
            outputStream.writeInt(2);
            outputStream.flush();

            String login = inputStream.readUTF();
            System.out.println("\nДобро пожаловать, " + login + "\n");


            // TODO: 27.03.2016 DELETE IT
        /*System.out.println("\nДобро пожаловать, " + journal.getUserLogin()
                + "\n");*/
            do {
                int countPoints = item.size();
                for (int i = 0; i < countPoints; i++) {
                    System.out.println("<" + (i + 1) + "> " + item.get(i).toString());
                }
                System.out.println("<0> Сохранить изменения и выйти\n");
                select = choice(0, countPoints);
                if (select == 0) {


                    // Сохранить список задач.
                    outputStream.writeInt(3);
                    outputStream.flush();

                    // TODO: 27.03.2016 DELETE IT
//                    journal.save();
                    System.out.println("До свидания!\n");
                } else {
                    try {
                        item.get(select - 1).show(inputStream, outputStream);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Вводите только числа!");
                    }
                }
            } while (select != 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ITEM;
    }
}