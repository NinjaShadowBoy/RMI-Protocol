package com.sji.rmiprotocoldemo.server;

import java.util.Stack;

public class Calculator {

    // Gives precedence of the operator
    private static int priority(char operator) {
        return switch (operator) {
            case '+', '-' -> 0;
            case '*', '/', '÷', '×' -> 1;
            case '%' -> 2;
            default -> -1;
        };
    }

    private static boolean isOperator(char operator) {
        return operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '%' || operator == '×' || operator == '÷';
    }

    // Computes the result of the given binary operator on the given operands
    private static Double compute(Double a, Double b, char operator) {
        return switch (operator){
            case '+'-> a + b;
            case '-'-> a - b;
            case '*', '×'->  a * b;
            case '/', '÷'-> a / b;
            default -> throw new IllegalArgumentException("Syntax error");
        };
    }

    public static Double evaluate(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Empty expression");
        }

        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        // Helper to apply a binary operator from the operator stack
        Runnable applyTopOperator = () -> {
            char op = operators.pop();
            if (operands.size() < 2) {
                throw new IllegalArgumentException("Syntax error");
            }
            double right = operands.pop();
            double left = operands.pop();
            operands.push(compute(left, right, op));
        };

        // Track whether we expect the start of a number (to allow unary minus)
        boolean expectNumber = true;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // Skip spaces if any
            if (Character.isWhitespace(ch)) continue;

            // Parse number (with optional unary minus)
            if (Character.isDigit(ch) || (ch == '-' && expectNumber) || (ch == '.' && expectNumber)) {
                StringBuilder num = new StringBuilder();

                // Unary minus at start of number
                if (ch == '-') {
                    num.append(ch);
                    i++;
                    if (i >= expression.length()) {
                        throw new IllegalArgumentException("Dangling '-'");
                    }
                    ch = expression.charAt(i);
                }

                // The rest of the numeric token
                boolean sawDot = false;
                while (i < expression.length()) {
                    char c = expression.charAt(i);
                    if (Character.isDigit(c)) {
                        num.append(c);
                        i++;
                    } else if (c == '.' && !sawDot) {
                        num.append(c);
                        sawDot = true;
                        i++;
                    } else {
                        break;
                    }
                }
                // Step back one because for-loop will i++
                i--;

                // Push parsed number
                if (num.toString().equals("-") || num.toString().equals(".") || num.toString().equals("-.")) {
                    throw new IllegalArgumentException("Invalid number");
                }
                operands.push(Double.parseDouble(num.toString()));
                expectNumber = false;
                continue;
            }

            // Postfix percent: divide last operand by 100
            if (ch == '%') {
                if (operands.isEmpty()) {
                    throw new IllegalArgumentException("Syntax error: misplaced %");
                }
                operands.push(operands.pop() / 100.0);
                expectNumber = false;
                continue;
            }

            // Binary operators
            if (isOperator(ch)) {
                // Apply operators with higher or equal precedence
                while (!operators.isEmpty() && priority(operators.peek()) >= priority(ch)) {
                    applyTopOperator.run();
                }
                operators.push(ch);
                expectNumber = true;
                continue;
            }

            // Unknown character
            throw new IllegalArgumentException("Invalid character: " + ch);
        }

        // Final reductions
        while (!operators.isEmpty()) {
            applyTopOperator.run();
        }

        if (operands.size() != 1) {
            throw new IllegalArgumentException("Syntax error");
        }
        return operands.pop();
    }
}
