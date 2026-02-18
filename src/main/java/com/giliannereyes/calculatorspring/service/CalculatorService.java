package com.giliannereyes.calculatorspring.service;

import org.springframework.stereotype.Service;
import java.util.Stack;

@Service
public class CalculatorService {

  public double calculate(String expression) {
    return evaluate(expression);
  }

  private double evaluate(String expr) {
    expr = expr.replaceAll("\\s+", "");

    Stack<Double> values = new Stack<>();
    Stack<Character> ops = new Stack<>();

    for (int i = 0; i < expr.length(); i++) {
      char ch = expr.charAt(i);

      // Handle number (including negative)
      if (Character.isDigit(ch) || ch == '.' ||
            (ch == '-' && (i == 0 || isOperator(expr.charAt(i - 1))))) {
        int start = i;
        do {
          i++;
        } while (i < expr.length() &&
              (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.'));
        double value = Double.parseDouble(expr.substring(start, i));
        values.push(value);
        i--;
      }
      else if (isOperator(ch)) {
        while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
          applyOperation(values, ops.pop());
        }
        ops.push(ch);
      }
    }
    while (!ops.isEmpty()) {
      applyOperation(values, ops.pop());
    }
    return values.pop();
  }

  private void applyOperation(Stack<Double> values, char op) {
    double b = values.pop();
    double a = values.pop();
    switch (op) {
      case '+' -> values.push(a + b);
      case '-' -> values.push(a - b);
      case '*' -> values.push(a * b);
      case '/' -> values.push(a / b);
    }
  }

  private boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/';
  }

  private int precedence(char op) {
    if (op == '+' || op == '-') return 1;
    if (op == '*' || op == '/') return 2;
    return 0;
  }
}
