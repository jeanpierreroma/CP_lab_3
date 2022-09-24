package cp.lab3;

import cp.lab3.manger.PrintManager;
import cp.lab3.workplace.WorkPlace;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        WorkPlace workPlace = new WorkPlace("firstFile.txt", "secondFile.txt");

        System.out.println("Усі вхідні дані");
        PrintManager.printArrayList(workPlace.getEmployees());
        System.out.println("\n");

        System.out.println("Посортовані дані без повторів");
        PrintManager.printArrayList(workPlace.getDataWithoutRepeating());
        System.out.println("\n");

        System.out.println("Частотна таблиця людей з заданою посадою");
        PrintManager.printMap(workPlace.getCountForAllOffices());
        System.out.println("\n");

        System.out.println("Таблиця дати зарахування і усіх прізвищ посортованих за посадою (від нижчої посади до вищої");
        PrintManager.printMap(workPlace.getAllSurnamesForAllDataOfEnrollment());
        System.out.println("\n");

        System.out.println("Без осіб, для яких на біжучу дату, зарахування відбулось більше 10 років назад.");
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        System.out.println("Біжуча дата " + timeStamp);
        PrintManager.printMap(workPlace.getAllSurnamesForAllDataOfEnrollemntBelowTen(timeStamp));
        System.out.println("\n");
    }
}