import java.util.Scanner;
import java.util.Stack;
public class InfixConversion {
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
        }
        return -1;
    }
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isLetterOrDigit(ch))
                result += ch;
            else if (ch == '(')
                stack.push(ch);
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch))
                    result += stack.pop();
                stack.push(ch);
            }
        }
        while (!stack.isEmpty())
            result += stack.pop();
        return result;
    }
    static String reverse(String exp) {
        return new StringBuilder(exp).reverse().toString();
    }
    static String infixToPrefix(String exp) {
        exp = reverse(exp);
        String temp = "";
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (ch == '(') temp += ')';
            else if (ch == ')') temp += '(';
            else temp += ch;
        }
        String postfix = infixToPostfix(temp);
        return reverse(postfix);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Infix Expression: ");
        String infix = scanner.nextLine();
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + infixToPostfix(infix));
        System.out.println("Prefix: " + infixToPrefix(infix));
        scanner.close();
    }
}