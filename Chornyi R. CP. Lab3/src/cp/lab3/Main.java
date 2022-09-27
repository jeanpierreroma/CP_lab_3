package cp.lab3;

import cp.lab3.manger.PrintManager;
import cp.lab3.workplace.WorkPlace;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WorkPlace workPlace = new WorkPlace("firstFile.txt", "secondFile.txt");
        boolean isProcess = true;
        do {
            System.out.println( "1.Вивід усіх даних зчитаних з двох файлів\n" +
                                "2.Вивід усіх даних без повторень прізвищ\n" +
                                "3.Частотну таблицю людей з заданою посадою\n" +
                                "4.Таблиця дати зарахування і усіх прізвищ посортованих за посадою (від нижчої посади до вищої)\n" +
                                "5.З пункту 4 вилучити всіх осіб, для яких на біжучу дату " +
                    "зарахування відбулось більше 10 років назад\n" +
                                "6.Завершити роботу програми");
            Scanner scanner = new Scanner(System.in);
            int chose = Integer.parseInt(scanner.next());
            switch (chose) {
                case 1:
                    System.out.println("Усі вхідні дані");
                    PrintManager.printArrayList(workPlace.getEmployees());
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.println("Посортовані дані без повторів");
                    PrintManager.printArrayList(workPlace.getDataWithoutRepeating());
                    System.out.println("\n");
                    break;
                case 3:
                    System.out.println("Частотна таблиця людей з заданою посадою");
                    PrintManager.printMap(workPlace.getCountForAllOffices(), String.format("%-25s%-25s", "Office", "Count of employees"));
                    System.out.println("\n");
                    break;
                case 4:
                    System.out.println("Таблиця дати зарахування і усіх прізвищ посортованих за посадою (від нижчої посади до вищої)");
                    PrintManager.printMap(workPlace.getAllSurnamesForAllDataOfEnrollment(), String.format("%-25s%-25s", "Data", "Surnames"));
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Без осіб, для яких на біжучу дату, зарахування відбулось більше 10 років назад.");
                    String timeStamp = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                    System.out.println("Біжуча дата " + timeStamp);
                    PrintManager.printMap(workPlace.getAllSurnamesForAllDataOfEnrollemntBelowTen(timeStamp), String.format("%-25s%-25s", "Data", "Surnames"));
                    System.out.println("\n");
                    break;
                case 6:
                    System.out.println("Роботу програми завершено!");
                    isProcess = false;
                    break;
                default:
                    System.out.println("Введено неправильну цифру");
            }
        } while (isProcess);
    }
}