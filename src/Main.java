import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String s = prompt("Введите данные через пробел: 'Фамилия Имя Отчество ДатаРождения(дд.мм.гггг) Телефон Пол(m/f)': ");
        String[] words = s.split(" ");
        run(words);
    }

    public static void run(String[] array) {
        lenghtCheck(array);
        checkDateFormat(array);
        checkPhoneFormat(array);
        checkSexFormat(array);

        String path = array[0] + ".txt";

        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(s);
            sb.append(" ");
        }

        try (FileWriter fw = new FileWriter(path, true)) {
            fw.append(String.valueOf(sb)).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void lenghtCheck(String[] array) throws RuntimeException {
        if (array.length < 6) {
            throw new RuntimeException("Данных меньше, чем нужно");
        }
        if (array.length > 6) {
            throw new RuntimeException("Данных больше чем нужно");
        }
    }

    public static void checkPhoneFormat(String[] array) throws NumberFormatException {
        try {
            Integer telNum = Integer.parseInt(array[4]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("В формате телефона допущена ошибка.");
        }
    }

    public static void checkDateFormat(String[] array) throws RuntimeException {
        try {
            String[] subStr = array[3].split("\\.");
            if (subStr.length != 3) {
                throw new RuntimeException("Неверный формат даты");
            }
            try {
                int day = Integer.parseInt(subStr[0]);
                if (day < 1 || day > 31) {
                    throw new RuntimeException("День указан неверно");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("День указан неверно");
            }
            try {
                int month = Integer.parseInt(subStr[1]);
                if (month < 1 || month > 12) {
                    throw new RuntimeException("Месяц указан неверно");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Месяц указан неверно");
            }
            try {
                int year = Integer.parseInt(subStr[2]);
                if (year < 1900 || year > 2024) {
                    throw new RuntimeException("Год указан неверно");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Год указан неверно");
            }
        } catch (NullPointerException e) {
            throw new RuntimeException("Неверный формат даты");
        }
    }

    public static void checkSexFormat(String[] array) throws NumberFormatException {
        try {
            char ch = array[5].charAt(0);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Пол предоставлен в неверном формате.");
        }
        if (!array[5].equals("m") && !array[5].equals("f")) {
            throw new NumberFormatException("Пол введен неверно.");
        }
    }

    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.println(message);
        return in.nextLine();
    }
}