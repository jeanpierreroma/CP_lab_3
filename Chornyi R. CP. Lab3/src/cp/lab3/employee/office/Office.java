package cp.lab3.employee.office;

public enum Office {
    TRAINEE("Trainee"),
    EMPLOYEE("Employee"),
    SENIOR_EMPLOYEE("Senior employee"),
    MANAGER("Manager"),
    DIRECTOR("Director");

    private final String office;

    Office(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return office;
    }
}
