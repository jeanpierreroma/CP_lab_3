package cp.lab3.employee;

import cp.lab3.employee.data.Data;
import cp.lab3.employee.office.Office;

public class Employee {
    private final String surname;
    private final Office office;
    private final Data dataOfEnrollment;

    public String getSurname() {
        return surname;
    }

    public Office getOffice() {
        return office;
    }

    public Data getDataOfEnrollment() {
        return dataOfEnrollment;
    }

    public Employee(String surname, String office, String dataOfEnrollment) {
        if(surname != null && surname.length() > 0) {
            this.surname = surname;
        } else {
            throw new RuntimeException("Поле surname пусте!");
        }
        if(office.equalsIgnoreCase("trainee") ||
                office.equalsIgnoreCase("employee") ||
                office.equalsIgnoreCase("senior_employee") ||
                office.equalsIgnoreCase("manager") ||
                office.equalsIgnoreCase("director")) {
            this.office = Office.valueOf(office.toUpperCase());
        } else {
            throw new IllegalArgumentException("Такої посади не передбачено");
        }

        String[] tmp = dataOfEnrollment.split("\\.");
        if(tmp.length == 3) {
            int day = Integer.parseInt(tmp[0]);
            int month = Integer.parseInt(tmp[1]);
            int year = Integer.parseInt(tmp[2]);
            this.dataOfEnrollment = new Data(day, month, year);
        } else {
            throw new IllegalArgumentException("Некоректна дата");
        }
    }

    @Override
    public String toString() {
        return String.format("%-25s%-25s%-25s", surname, office.toString(), dataOfEnrollment.toString());
    }
}
