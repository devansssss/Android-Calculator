package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;

import com.google.android.material.button.MaterialButton;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button zero, one, two, three, four, five, six, seven, eight, nine, equal, multiply, decimal, plus, divide, delete, percentage, clear, minus, bopen, bclose;
    private TextView result, Res;
    private int Result;
    private String calculate;


    private int Numbers[];
    private char[] Op;
    private int Close;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        equal = findViewById(R.id.equal);
        multiply = findViewById(R.id.multiply);
        decimal = findViewById(R.id.decimal);
        plus = findViewById(R.id.plus);
        divide = findViewById(R.id.divide);
        delete = findViewById(R.id.delete);
        clear = findViewById(R.id.Clear);
        minus = findViewById(R.id.minus);
        result = findViewById(R.id.Result);
        bopen = findViewById(R.id.bopen);
        bclose = findViewById(R.id.bclose);
        equal.setOnClickListener(this);
        Res = findViewById(R.id.Res);
        minus.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.operator)));
        plus.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.operator)));
        multiply.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.operator)));
        divide.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.operator)));
        equal.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.operator)));



        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        decimal.setOnClickListener(this);
        divide.setOnClickListener(this);
        plus.setOnClickListener(this);
        bopen.setOnClickListener(this);
        bclose.setOnClickListener(this);


        Numbers = new int[50];
        Op = new char[50];
        Result = 0;
        Close = 0;

    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String datatocalculate = result.getText().toString();
        boolean b = datatocalculate.endsWith("*") || datatocalculate.endsWith("+") || datatocalculate.endsWith("-") || datatocalculate.endsWith("%") || datatocalculate.endsWith("/");
        if(view.getId()==R.id.delete){
            if(datatocalculate.length()>0){
                if(datatocalculate.endsWith("(")){
                    Close=0;
                    Result--;
                } else if (datatocalculate.endsWith(")")) {
                    Result++;
                    Close=0;
                }
                datatocalculate = datatocalculate.substring(0, datatocalculate.length()-1);
                result.setText(datatocalculate);
            }
        } else if (view.getId()==R.id.one || view.getId()==R.id.two || view.getId()==R.id.three || view.getId()==R.id.four || view.getId()==R.id.five || view.getId()==R.id.six || view.getId()==R.id.seven || view.getId()==R.id.eight || view.getId()==R.id.nine || view.getId()==R.id.zero) {
            datatocalculate+=buttonText;
            result.setText(datatocalculate);
        } else if (view.getId()==R.id.Clear) {
            datatocalculate="";
            result.setText(datatocalculate);
            Result=0;
            Res.setText("");
            Close=0;
        } else if (view.getId()==R.id.minus || view.getId()==R.id.multiply || view.getId()==R.id.plus || view.getId()==R.id.divide) {
            if(view.getId()==R.id.multiply) {
                if (!b) {
                    datatocalculate+="*";
                    result.setText(datatocalculate);
                    Close=0;
                }
            }
            else{
                if(!b){
                    Close=0;
                    datatocalculate+=buttonText;
                    result.setText(datatocalculate);
                }
            }
        } else if (view.getId()==R.id.equal){
            if (Result!=0){
                Toast.makeText(this, "Invalid Syntax", Toast.LENGTH_SHORT).show();
            }
            else {
                List<String> postfixExpression = ShuntingYardParser.parse(datatocalculate);
                double res = PostfixEvaluator.evaluate(postfixExpression);
                String haha = Double.toString(res);
                Res.setText(haha);
            }
        } else if (view.getId()==R.id.bopen || view.getId()==R.id.bclose){
            if(view.getId()==R.id.bopen){
                    Result++;
                    datatocalculate+=buttonText;
                    result.setText(datatocalculate);
            }
            else if (view.getId()==R.id.bclose && Result>0){
                if (datatocalculate.endsWith("0") || datatocalculate.endsWith("1") || datatocalculate.endsWith("2") || datatocalculate.endsWith("3") || datatocalculate.endsWith("4") || datatocalculate.endsWith("5") || datatocalculate.endsWith("6") || datatocalculate.endsWith("7") || datatocalculate.endsWith("8") || datatocalculate.endsWith("9") || datatocalculate.endsWith(")") ){
                    Result--;
                    datatocalculate+=buttonText;
                    result.setText(datatocalculate);
                }
            }
        } else if (view.getId()==R.id.decimal) {
            if (datatocalculate.endsWith(".") || datatocalculate.endsWith("*") || datatocalculate.endsWith("/") || datatocalculate.endsWith("-") || datatocalculate.endsWith("+")|| datatocalculate.endsWith("(") || datatocalculate.endsWith(")")){datatocalculate+=".";
            }
            else if (Close==0){
                Close=1;
                datatocalculate+=buttonText;
                result.setText(datatocalculate);
            }
        }
    }

    private void calc() {

    }

    private static class PostfixEvaluator {
        public static double evaluate(List<String> postfixExpression) {
            Deque<Double> operandStack = new ArrayDeque<>();

            for (String token : postfixExpression) {
                if (isNumber(token)) {
                    double operand = Double.parseDouble(token);
                    operandStack.push(operand);
                } else if (isOperator(token)) {
                    double rightOperand = operandStack.pop();
                    double leftOperand = operandStack.pop();
                    double result = applyOperator(leftOperand, rightOperand, token);
                    operandStack.push(result);
                }
            }

            return operandStack.pop();
        }

        private static boolean isNumber(String token) {
            return token.matches("-?\\d+(\\.\\d+)?");
        }

        private static boolean isOperator(String token) {
            return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
        }

        private static double applyOperator(double leftOperand, double rightOperand, String operator) {
            switch (operator) {
                case "+":
                    return leftOperand + rightOperand;
                case "-":
                    return leftOperand - rightOperand;
                case "*":
                    return leftOperand * rightOperand;
                case "/":
                    return leftOperand / rightOperand;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
    }




    private static class ShuntingYardParser {
        private static final Map<String, Integer> OPERATOR_PRECEDENCE;

        static {
            OPERATOR_PRECEDENCE = new HashMap<>();
            OPERATOR_PRECEDENCE.put("+", 2);
            OPERATOR_PRECEDENCE.put("-", 2);
            OPERATOR_PRECEDENCE.put("*", 3);
            OPERATOR_PRECEDENCE.put("/", 3);
        }

        public static List<String> parse(String infixExpression) {
            List<String> postfixExpression = new ArrayList<>();
            Deque<String> operatorStack = new ArrayDeque<>();

            String[] tokens = tokenize(infixExpression);

            for (String token : tokens) {
                if (isNumber(token)) {
                    postfixExpression.add(token);
                } else if (isOperator(token)) {
                    while (!operatorStack.isEmpty() && isOperator(operatorStack.peek())
                            && getPrecedence(operatorStack.peek()) >= getPrecedence(token)) {
                        postfixExpression.add(operatorStack.pop());
                    }
                    operatorStack.push(token);
                } else if (token.equals("(")) {
                    operatorStack.push(token);
                } else if (token.equals(")")) {
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                        postfixExpression.add(operatorStack.pop());
                    }
                    if (!operatorStack.isEmpty() && operatorStack.peek().equals("(")) {
                        operatorStack.pop();
                    }
                }
            }

            while (!operatorStack.isEmpty()) {
                postfixExpression.add(operatorStack.pop());
            }

            return postfixExpression;
        }

        private static String[] tokenize(String expression) {
            // Remove all whitespace from the expression
            expression = expression.replaceAll("\\s+", "");

            // Use a regular expression to split the expression into tokens
            return expression.split("(?=[-+*/()])|(?<=[-+*/()])");
        }

        private static boolean isNumber(String token) {
            return token.matches("-?\\d+(\\.\\d+)?");
        }

        private static boolean isOperator(String token) {
            return OPERATOR_PRECEDENCE.containsKey(token);
        }

        private static int getPrecedence(String operator) {
            return OPERATOR_PRECEDENCE.getOrDefault(operator, 0);
        }
    }


}
