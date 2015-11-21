package o26;

import o26.controller.DataLoader;
import o26.controller.Journal;

public class Main {

    public static void main(String[] args){
        DataLoader dataLoader = new DataLoader();

        Journal journal = new Journal();
        journal.setDataLoader(dataLoader);

        journal.showMenu();
    }
}