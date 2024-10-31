package view;

public class Menu {


    private void waitRead() {
        System.out.print("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

    // Возвращает только введённое число (предотвращая ошибку в случае ввода символов)

    private int getSelection() {
        int selection;
        while (true) {
            System.out.printf("Введите Ваш выбор: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Вы ввели не число. Повторите ввод.");
                scanner.nextLine();
            } else {
                selection = scanner.nextInt();
                scanner.nextLine();
                return selection;
            }
        }
    }

}
