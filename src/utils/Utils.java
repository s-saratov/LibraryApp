package utils;

public class Utils {

    // Метод проверяет адрес электронной почты на соответствие требованиям

    public static boolean isEmailValid(String email) {

        if (email == null) return false; // исключаем передачу null

        // 1. Должен присутствовать @, и только один

        int indexAt = email.indexOf('@'); // индекс символа @ в строке
        // int lastAt = email.lastIndexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) return false;

        // 2. После @ должна быть точка

        int dotIndexAfterAt = email.indexOf('.', indexAt + 1); // индекс точки
        if (dotIndexAfterAt == -1) return false;

        // 3. После последней точки есть два и более символа

        int lastDotIndex = email.lastIndexOf('.'); // индекс последней точки
        if ((email.length() - lastDotIndex) < 3) return false;

        // 4. Латинский алфавит, цифры, '-', '_', '.', '@'

        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.@"; // допустимые символы
        for (int i = 0; i < email.length(); i++) {
            if (validChars.indexOf(email.charAt(i)) == -1) return false;
        }

        // 5. До @ должен быть хотя бы один символ

        if (indexAt == 0) return false;

        // 6. Первым символом должна быть буква

        if (!Character.isLetter(email.charAt(0))) return false;

        return true;

    }

    // Метод проверяет пароль на соответствие требованиям

    public static boolean isPasswordValid(String password) {

        if (password == null) return false; // исключаем передачу null

        String specialChars = "!%$@&*()[].,-";

        boolean c1 = password.length() > 7; // условие № 1: длина >= 8 символов

        boolean c2 = false;
        boolean c3 = false;
        boolean c4 = false;
        boolean c5 = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) c2 = true; // условие № 2: минимум 1 цифра
            if (Character.isLowerCase(password.charAt(i))) c3 = true; // условие № 3: минимум 1 маленькая буква
            if (Character.isUpperCase(password.charAt(i))) c4 = true; // условие № 4: минимум 1 большая буква
            if (specialChars.indexOf(password.charAt(i)) != -1) c5 = true; // условие № 5: минимум 1 спецсимвол (!%$@&*()[].,-)

        }

        return c1 && c2 && c3 && c4 && c5;

    }

}
