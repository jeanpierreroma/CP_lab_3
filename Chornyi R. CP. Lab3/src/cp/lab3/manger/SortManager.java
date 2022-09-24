package cp.lab3.manger;

import cp.lab3.employee.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class SortManager {
    public static ArrayList<Employee> sortBySurname(ArrayList<Employee> employeeArrayList) {
        var resList = new ArrayList<>(employeeArrayList);

        resList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        });

        return resList;
    }
    public static ArrayList<Employee> sortByDataOfEnrollment(ArrayList<Employee> employeeArrayList) {
        var resList = new ArrayList<>(employeeArrayList);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Collections.sort(resList, (s1, s2) -> LocalDate.parse(s1.getDataOfEnrollment().toString(), formatter).
                compareTo(LocalDate.parse(s2.getDataOfEnrollment().toString(), formatter)));

        return resList;
    }

    public static ArrayList<Employee> sortByOffice(ArrayList<Employee> employeeArrayList) {
        var resList = new ArrayList<>(employeeArrayList);

        resList.sort(Comparator.comparing(Employee::getOffice));

        return resList;
    }
}
