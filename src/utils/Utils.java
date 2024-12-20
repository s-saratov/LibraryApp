package utils;

import model.Book;
import model.User;

import java.math.BigInteger;
import java.time.Year;
import java.util.Arrays;

public class Utils {

    /**
     * Проверяет, является ли строка валидным доменом.
     *
     * @param domain Строка.
     * @return {@code true}, если строка является валидным доменом; иначе {@code false}.
     */
    public static boolean isValidDomain(String domain) {
        if (domain == null || domain.isEmpty() || domain.startsWith(".") || domain.endsWith(".")) {
            return false;
        }

        String[] parts = domain.split("\\.");

        if (parts.length < 2) {
            return false;
        }

        for (int i = parts.length - 1; i >= 0; i--) {
            String part = parts[i];

            if (part == null || part.isEmpty()) {
                return false;
            }

            if (i == 0) {
                if (part.length() == 1) {
                    return false;
                }

                for (char ch : part.toCharArray()) {
                    if (!Character.isLetterOrDigit(ch)) {
                        return false;
                    }
                }
            } else {
                if (part.startsWith("-") || part.endsWith("-")) {
                    return false;
                }

                for (char ch : part.toCharArray()) {
                    if (!Character.isLetterOrDigit(ch) && ch != '-') {
                        return false;
                    }
                }
            }
        }

        return (parts[parts.length - 1].length() >= 2);
    }


    /**
     * Проверяет, является ли строка валидным email.
     *
     * @param email Строка.
     * @return {@code true}, если строка является валидным email; иначе {@code false}.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String[] parts = email.split("@");

        if (parts.length != 2) {
            return false;
        }

        String username = parts[0];

        if (
            username == null ||
            username.isEmpty() ||
            !Character.isAlphabetic(username.charAt(0)) ||
            !Character.isAlphabetic(username.charAt(username.length() - 1))
        ) {
            return false;
        }

        for (int i = 0; i < username.length(); i++) {
            char ch = username.charAt(i);

            if (!(
                Character.isAlphabetic(ch) ||
                Character.isDigit(ch) ||
                ch == '-' ||
                ch == '_' ||
                ch == '.'
            )) {
                return false;
            }
        }

        return isValidDomain(parts[1]);
    }


    /**
     * Проверяет, является ли строка валидным паролем.
     *
     * @param password Строка.
     * @return {@code true}, если строка является валидным паролем; иначе {@code false}.
     */
    public static boolean isValidPassword(String password) {
        /*
         * `0` - длина `>= 8`.
         * `1` - Должна быть мин 1 цифра
         * `2` - Должна быть мин 1 маленькая буква
         * `3` - Должна быть мин 1 большая буква
         * `4` - Должна быть мин 1 спец. символ (`!%$@&*()[].,-`)
         */
        boolean[] result = new boolean[5];

        if (!(password == null || password.length() < 8)) {
            result[0] = true;

            String symbols = "!%$@&*()[].,-";

            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);

                if (Character.isDigit(ch)) {
                    result[1] = true;
                }

                if (Character.isLowerCase(ch)) {
                    result[2] = true;
                }

                if (Character.isUpperCase(ch)) {
                    result[3] = true;
                }

                if (symbols.indexOf(ch) >= 0) {
                    result[4] = true;
                }
            }
        }

        // NOTE: Можно вернуть массив, если требуется указать, какие именно проверки не выполнены.
        // System.out.println(Arrays.toString(result));

        for (boolean item : result) {
            if (!item) {
                return false;
            }
        }

        return true;
    }


    // Возвращает список книг в табличном формате
    public static String printBooks(MyList<Book> books) {
        String result = String.format("\u001B[33m%-5s %-20s %-35s %-10s %-25s\u001B[0m\n", "ID:", "Author:", "Title:", "Year:", "Publisher:");
        for (Book book : books) {
            result = result.concat(String.format("%-5d %-20s %-35s %-10d %-25s\n",
                    book.getId(), book.getAuthor(), book.getTitle(), book.getYear(), book.getPublisher()));
        }
        return result;
    }

    // Возвращает список книг в табличном формате (для администратора)
    public static String printBooksAdmin(MyList<Book> books) {
        String result = String.format("\u001B[33m%-5s %-20s %-35s %-7s %-25s %-10s %s\u001B[0m\n",
                "ID:", "Author:", "Title:", "Year:", "Publisher:", "Status", "Borrower:");
        for (Book book : books) {
            result = result.concat(String.format("%-5d %-20s %-35s %-7d %-25s %-10s %s\n",
                    book.getId(),
                    book.getAuthor(),
                    book.getTitle(),
                    book.getYear(),
                    book.getPublisher(),
                    book.getStatus(),
                    (book.getBorrower() == null) ? "-" : book.getBorrower().getEmail()
            ));
        }
        return result;
    }


    // Возвращает список пользователей в табличном формате
    public static String printUsers(MyList<User> users) {
        String result = String.format("\u001B[33m%-5s %-30s %-30s %-15s\u001B[0m\n", "ID:", "Email:", "Password:", "Role:");
        for (User user : users) {
            result = result.concat(String.format("%-5d %-30s %-30s %-15s\n",
                    user.getId(), user.getEmail(), user.getPassword(), user.getRole()));
        }
        return result;
    }

}
