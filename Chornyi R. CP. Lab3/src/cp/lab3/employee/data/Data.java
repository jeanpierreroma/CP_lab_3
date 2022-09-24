package cp.lab3.employee.data;

public class Data {
    private final int day;
    private final int month;
    private final int year;

    public Data(int day, int month, int year) {
        if(year > 0) {
            this.year = year;
        }
        else {
            throw new IllegalArgumentException("Рік повинен бути більше 0");
        }

        if(!(month > 12 || month < 0)) {
            this.month = month;
        }
        else {
            throw new IllegalArgumentException("Місяць має бути в проміжку від 0 до 12");
        }

        if(!(day < 0 || day > 31)) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    this.day = day;
                    break;
                case 2:
                    if(day <= 29) {
                        if(day != 29) {
                            this.day = day;
                        } else {
                            if(year % 5 == 0) {
                                this.day = day;
                            } else {
                                throw new IllegalArgumentException("Цього року в грудні лише 28 днів");
                            }
                        }
                    } else
                        throw new IllegalArgumentException("В грудні лише 29 днів");
                    break;
                default:
                    if(day <= 30) {
                        this.day = day;
                    } else {
                        throw new IllegalArgumentException("У цьому місяці лише 30 днів");
                    }
            }
        } else {
            throw new IllegalArgumentException("День має бути в проміжку від 0 до 31");
        }
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }
}
