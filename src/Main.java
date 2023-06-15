import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in); // сканер ввода
        String[] phrase;                        // введенная фраза
        int firstOperand;                       // первый операнд
        int secondOperand;                      // второй операнд
        boolean isRoman;                        // если римское число
        int result;                             // результат
        String output;                          // вывод

        while (true) {
            phrase = input.nextLine().split(" "); // ввод фразы и разделение на составляющие

            if (phrase.length != 3) // если составляющих не 3
                throw new Exception("Формат математической операции не удовлетворяет заданию - " +
                                    "два операнда и один оператор (+, -, /, *)");

            try { // преобразование строки в арабские числа
                firstOperand = Integer.parseInt(phrase[0]);
                secondOperand = Integer.parseInt(phrase[2]);
                isRoman = false;
            }
            catch (Exception e) { // при исключении преобразование строки в римские числа
                firstOperand = romanToArabic(phrase[0]);
                secondOperand = romanToArabic(phrase[2]);
                isRoman = true;
            }

            if (firstOperand > 10 || secondOperand > 10 || firstOperand < 1 || secondOperand < 1)
                throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более");

            switch (phrase[1]) { // проведение математической операции в зависимости от указанного операнда
                case "+" -> result = firstOperand + secondOperand;
                case "-" -> result = firstOperand - secondOperand;
                case "*" -> result = firstOperand * secondOperand;
                case "/" -> result = firstOperand / secondOperand;
                default -> throw new Exception("Неверный оператор математической операции");
            }

            if (isRoman) {
                if (result < 1)
                    throw new Exception("В римской системе нет нуля и отрицательных чисел");
                else
                    output = arabicToRoman(result);
            }
            else
                output = String.valueOf(result);

            System.out.println(output);
        }
    }

    public static int romanToArabic(String number) throws Exception { // преобразование римского числа в арабское
        int result = 0;

        switch (number) {
            case "I" -> result = 1;
            case "II" -> result = 2;
            case "III" -> result = 3;
            case "IV" -> result = 4;
            case "V" -> result = 5;
            case "VI" -> result = 6;
            case "VII" -> result = 7;
            case "VIII" -> result = 8;
            case "IX" -> result = 9;
            case "X" -> result = 10;
            default -> throw new Exception("Неизвестный операнд");
        }

        return result;
    }

    public static String arabicToRoman(int number) { // преобразование арабского числа в римское
        String[] romanNumerals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < arabicValues.length; i++) {
            while (number >= arabicValues[i]) {
                number -= arabicValues[i];
                result.append(romanNumerals[i]);
            }
        }

        return result.toString();
    }
}