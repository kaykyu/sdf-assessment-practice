package calculator;

import java.io.Console;

public class Main {

    private static double $last = 0;
    private static double a;
    private static double b;

    public static double add(double a, double b) {
        return a + b;
    }

    public static double minus(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }

    public static void main(String[] args) {
        
        String input = "";
        Console cons = System.console();
        input = cons.readLine("Welcome.\n> ");

        while (!input.equals("exit")) {
            String request[] = input.trim().split(" ");

            switch (request[0]) {
                case "$last": a = $last;
                    break;
                default: a = Double.parseDouble(request[0]);
            }
            
            switch (request[2]) {
                case "$last": b = $last;
                    break;
                default: b = Double.parseDouble(request[2]);
            }
            
            switch (request[1]) {
                case "+": $last = add(a, b); 
                    break;
                case "-": $last = minus(a, b);
                    break;
                case "*": $last = multiply(a, b);
                    break;
                case "/": $last = divide(a, b);
                    break;
            }

            input = cons.readLine("%.2f\n> ", $last);
        }

        System.out.println("Bye bye");
    }
} 