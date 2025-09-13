package com.sji.rmiprotocoldemo.server;

import java.util.Stack;

public class Calculator {

    private static int priority(char operator) {
        return switch (operator) {
            case '+', '-' -> 0;
            case '*', '/' -> 1;
            case '%' -> 2;
            default -> -1;
        };
    }

    private static boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '%';
    }

    private static Double compute(Double a, Double b, char operator) {
        return switch (operator){
            case '+'-> a + b;
            case '-'-> a - b;
            case '*'->  a * b;
            case '/'-> a / b;
            default -> throw new IllegalArgumentException("Syntax error");
        };

    }

    public static Double evaluate(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char character = expression.charAt(i);

            if (Character.isDigit(character)) {
                StringBuilder num = new StringBuilder();
                while (i < expression.length()) {
                    if (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.') {
                        num.append(expression.charAt(i));
                        i++;
                    }
                }
                operands.push(Double.parseDouble(num.toString()));
                i--;
            } else if(isOperator(character)) {
                if (!operands.empty() && priority(operators.peek()) >= priority(character)) {
                    char _operator = operators.pop();
                    char operator = _operator == '%' ? '/' : operators.pop(); //If the operator is %, then our operator becomes division
                    double value1 = operands.pop();
                    double value2 = operator == '%' ? 100 : operands.pop(); //If the operator is % then the second value is 100

                    operands.push(compute(value1, value2, operator));
                }
                operators.push(character);

            }
        }

        while (!operators.empty()) {
            char operator = operators.pop();
            double value1 = operands.pop();
            double value2 = operands.pop();
            operands.push(compute(value1, value2, operator));
        }
        return operands.pop();
    }
}
