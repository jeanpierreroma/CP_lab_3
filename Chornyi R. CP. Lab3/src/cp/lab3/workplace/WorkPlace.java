package cp.lab3.workplace;

import cp.lab3.employee.Employee;
import cp.lab3.manger.SortManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WorkPlace {
    private final ArrayList<Employee> employees;

    public ArrayList<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public WorkPlace(String firstFileName, String secondFileName) {
        this.employees = new ArrayList<>();

        //Читаємо з першого файлу
        try (FileReader reader = new FileReader(firstFileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] arrTmp = tmp.split(" ");
                employees.add(new Employee(arrTmp[0], arrTmp[1], arrTmp[2]));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        //Читаємо з другого файлу
        try (FileReader reader = new FileReader(secondFileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                String[] arrTmp = tmp.split(" ");
                employees.add(new Employee(arrTmp[0], arrTmp[1], arrTmp[2]));
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public WorkPlace(Employee employee) {
        this.employees = new ArrayList<>();
        this.employees.add(employee);
    }

    public ArrayList<Employee> getDataWithoutRepeating() {
        //Посортований список за прізвищами
        ArrayList<Employee> employeesWithoutRepeating = SortManager.sortBySurname(getEmployees());

        for (int i = 0; i < employeesWithoutRepeating.size() - 1; i++) {
            if(employeesWithoutRepeating.get(i).getSurname().equals(employeesWithoutRepeating.get(i + 1).getSurname())) {
                employeesWithoutRepeating.remove(i);
            }
        }

        return employeesWithoutRepeating;
    }

    public LinkedHashMap<String, Integer> getCountForAllOffices() {
        ArrayList<Employee> employeeArrayList = getDataWithoutRepeating();

        int traineeCount, employeeCount, senior, manager, director;
        traineeCount = employeeCount = senior = manager = director = 0;

        for (Employee employee : employeeArrayList) {
            switch (employee.getOffice()) {
                case TRAINEE -> traineeCount++;
                case EMPLOYEE -> employeeCount++;
                case SENIOR_EMPLOYEE -> senior++;
                case MANAGER -> manager++;
                case DIRECTOR -> director++;
            }
        }


        LinkedHashMap<String, Integer> countOffice = new LinkedHashMap<>();
        countOffice.put("TRAINEE", traineeCount);
        countOffice.put("EMPLOYEE", employeeCount);
        countOffice.put("SENIOR_EMPLOYEE", senior);
        countOffice.put("MANAGER", manager);
        countOffice.put("DIRECTOR", director);

        return countOffice;
    }

    public LinkedHashMap<String, List<String>> getAllSurnamesForAllDataOfEnrollment() {
        ArrayList<Employee> employeeArrayList = getDataWithoutRepeating();
        ArrayList<Employee> dataList = SortManager.sortByDataOfEnrollment(employeeArrayList);

        LinkedHashMap<String, List<String>> dataAndSurnames = new LinkedHashMap<>();

        ArrayList<ArrayList<Employee>> s1 = new ArrayList<>();
        for (int i = 0, j; i < dataList.size(); i++) {
            ArrayList<Employee> s2 = new ArrayList<>();
            for (j = i; j < dataList.size() && dataList.get(i).getDataOfEnrollment().toString().equals(dataList.get(j).getDataOfEnrollment().toString()); j++) {
                s2.add(dataList.get(j));
            }
            i = j - 1;
            s1.add(s2);
        }

        //Сортування за посадою
        for (int i = 0; i < s1.size(); i++) {
            if(s1.get(i).size() > 1) {
                s1.set(i, SortManager.sortByOffice(s1.get(i)));
            }
        }

        //i for s1, j for insideArray, k for dataList
        for (int i = 0, j, k = 0; i < s1.size() && k < dataList.size(); i++, k++) {
            List<String> list = new ArrayList<>();
            for (j = 0; j < s1.get(i).size(); j++) {
                list.add(s1.get(i).get(j).getSurname());
            }
            k += --j;
            dataAndSurnames.put(dataList.get(k).getDataOfEnrollment().toString(), list);
        }



        return dataAndSurnames;
    }

    public LinkedHashMap<String, List<String>> getAllSurnamesForAllDataOfEnrollemntBelowTen(String time) {
        Map<String, List<String>> allData = getAllSurnamesForAllDataOfEnrollment();

        var now = time.split("-");
        int thisYear = Integer.parseInt(now[2]);

        LinkedHashMap<String, List<String>> resMap = new LinkedHashMap<>();
        for(Map.Entry<String, List<String>> entry : allData.entrySet()) {
            var dataOfEnt = entry.getKey().split("-");
            int yearOfEnt = Integer.parseInt(dataOfEnt[2]);

            if(thisYear - yearOfEnt <= 20) {
                resMap.put(entry.getKey(), entry.getValue());
            }
        }

        return resMap;
    }
}
