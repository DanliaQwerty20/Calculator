import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
    private static final Map<String, Integer> romanNumerals = new HashMap<>();

    static {
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);
    }

    private boolean isRoman(String numStr) {
        return numStr.matches("^[IVXLCDM]+$");
    }

    private int toArabic(String numStr) {
        return romanNumerals.get(numStr.toUpperCase());
    }

    private String toRoman(int num) {
        for (Map.Entry<String, Integer> entry : romanNumerals.entrySet()) {
            if (entry.getValue() == num) {
                return entry.getKey();
            }
        }
        return null;
    }

    private String calculate(String expression) throws Exception {
        String[] operands = expression.split(" ");
        if (operands.length != 3) {
            throw new IllegalArgumentException("Invalid input: Incorrect number of operands");
        }

        String num1 = operands[0];
        String operator = operands[1];
        String num2 = operands[2];

        if (!(1 <= (isRoman(num1) ? toArabic(num1) : Integer.parseInt(num1)) && (isRoman(num1) ? toArabic(num1) : Integer.parseInt(num1)) <= 10) ||
                !(1 <= (isRoman(num2) ? toArabic(num2) : Integer.parseInt(num2)) && (isRoman(num2) ? toArabic(num2) : Integer.parseInt(num2)) <= 10)) {
            throw new IllegalArgumentException("Invalid input: Operands should be between 1 and 10");
        }

        if ((isRoman(num1) && !isRoman(num2)) || (!isRoman(num1) && isRoman(num2))) {
            throw new IllegalArgumentException("Invalid input: Mixing Roman and Arabic numerals");
        }

        int result;
        switch (operator) {
            case "+":
                result = (isRoman(num1) ? toArabic(num1) : Integer.parseInt(num1)) + (isRoman(num2) ? toArabic(num2) : Integer.parseInt(num2));
                break;
            case "-":
                result = (isRoman(num1) ? toArabic(num1) : Integer.parseInt(num1)) - (isRoman(num2) ? toArabic(num2) : Integer.parseInt(num2));
                break;
            case "*":
                result = (isRoman(num1) ? toArabic(num1) : Integer.parseInt(num1)) * (isRoman(num2) ? toArabic(num2) : Integer.parseInt(num2));
                break;
            case "/":
                result = (isRoman(num1) ? toArabic(num1) : Integer.parseInt(num1)) / (isRoman(num2) ? toArabic(num2) : Integer.parseInt(num2));
                break;
            default:
                throw new IllegalArgumentException("Invalid input: Unsupported operator");
        }

        if (isRoman(num1)) {
            if (result <= 0) {
                throw new IllegalArgumentException("Invalid output: Roman numerals cannot represent zero or negative numbers");
            }
            return toRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Input:\n");
            String userInput = scanner.nextLine();
            String result = calculator.calculate(userInput);
            System.out.println("Output:\n" + result);
        } catch (Exception e) {
            System.out.println("Output:\nthrows Exception //" + e.getMessage());
        }
    }
}
