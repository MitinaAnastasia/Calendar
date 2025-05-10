import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Для получения календаря введите год (или 0, если хотите прекратить): ");

            if (!input.hasNextInt()) {
                System.out.println("Повторите ввод. Год должен быть целым числом");
                input.next();
                continue;
            }

            int year = input.nextInt();

            if (year == 0) {
                System.out.println("Конец программы");
                break;
            }

            if (year < 1 || year > LocalDate.MAX.getYear()) {
                System.out.println("Повторите ввод. Минимальный год: 1, Максимальный год: 999999999");
                continue;
            }

            Calendar calendar = new Calendar(year);
            calendar.print();
        }
        input.close();
    }
}