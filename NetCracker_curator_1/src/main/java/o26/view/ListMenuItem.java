package o26.view;

import java.io.*;

public class ListMenuItem extends AbstractMenuItem {
    private static final String ITEM = "Просмотр списка задач";

    @Override
    @SuppressWarnings("unchecked")
    public void show(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        int countTask;

        try {
            outputStream.writeInt(6);
            outputStream.flush();
            countTask = inputStream.readInt();

            if (countTask == 0) {
                System.out.println("Список задач пуст!\n");
            } else {
                StringBuilder breakingLine = new StringBuilder("");
                StringBuilder border = new StringBuilder("~");
                for (int i = 0; i < countTask; i++) {
                    int lengthLine = 40 - i - 2;
                    for (int j = 0; j < lengthLine; j++) {
                        breakingLine.append(border);
                    }
                    String task = inputStream.readUTF();
                    System.out.println("<" + (i + 1) + ">" + breakingLine);
                    System.out.println(task);
                    System.out.println("<" + (i + 1) + ">" + breakingLine);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ITEM;
    }
}