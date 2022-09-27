package cp.lab3.manger;

import cp.lab3.employee.Employee;

import java.util.*;

public class PrintManager {
    public static void printArrayList(ArrayList<Employee> arrayList) {
        System.out.println(String.format("%-25s%-25s%-25s", "Surname", "Office", "Data of Enrollment"));
        for (Employee tmp : arrayList) {
            System.out.println(tmp.toString());
        }
    }

    public static void printMap(LinkedHashMap map, String format) {
        System.out.println(format);


        LinkedHashMap<String, List<String>> m = new LinkedHashMap<>(map);

        for (Map.Entry<String, List<String>> entry : m.entrySet()) {
            System.out.println(String.format("%-25s%-25s", entry.getKey(), entry.getValue()));
        }

    }
}
