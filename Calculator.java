// Calculator.java

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class creates a calculator, both the GUI and the back end logic.
 * The buttons are created by two different classes, NumButton and OpButton.
 * This allows for pre-initialization of button characteristics, and they
 * both share a single listener.  The listener directs the events via an
 * ActionCommand, this directs the action event to either the doNumber method
 * (which builds and displays the numbers) or the doOperation method (which
 * handles the mathmatical operations).
 *
 * @author   Chris Fields
 * @version  Last modified 04_11_16
 **/
class Calculator extends JFrame
{
   private JTextField numDisplay;
   private NumButton one, two, three, four, five, six, seven, eight, nine, zero, dec;
   private OpButton clear, sqroot, div, mult, minus, plus, equal;
   private double numOne, numTwo = 0.0;
   private String number = "";
   private String operator = "";
   private String secondOperator = "";
   private int toggle = 0;

   public static void main(String [] args)
   {
      Calculator s = new Calculator();
   }

  /**
   * This method receives a String representation of the numerical buttons from
   * the calculator.  It then builds the complete number as the individual digits
   * are received, while displaying the number on the calculator display.  It
   * limits the number of decimals to one, and allows only a single leading zero.
   * The toggle variable allows the first number entered to become the first
   * number of operation (numOne), and then all further numbers imput by the user
   * are treated as the second number of operation (numTwo).
   **/
   public void doNumber(String s)
   {
      if (toggle == 0)
      {
         number += s;
         int countDec = 0;
         for (int i = 0; i < s.length(); i++)
         {
            if (s.charAt(i) == '.')
            {
               countDec ++;
               if (countDec == 1)
               {
                  dec.setEnabled(false);
               }
            }
         }
         numOne = Double.parseDouble(number);
         numDisplay.setText(Double.toString(numOne));
      }
      else
      {
         number += s;
         int countDec = 0;
         for (int i = 0; i < s.length(); i++)
         {
            if (s.charAt(i) == '.')
            {
               countDec ++;
               if (countDec == 1)
               {
                  dec.setEnabled(false);
               }
            }
         }
         numTwo = Double.parseDouble(number);
         numDisplay.setText(Double.toString(numTwo));
      }
   }

  /**
   * This method reveives a String representation of the operator buttons from
   * the calcualtor.  It then carries out the appropriate operations and
   * displays the result on the calculator display.
   *
   * There are two operator variables (operator and secondOperator), which allows
   * for continuous calculations.  The toggle variable allows the first number
   * entered to become the first number of operation (numOne), and then all further
   * numbers imput by the use are treated as the second number of operation (numTwo).
   **/
   public void doOperation(String op)
   {
      number = "";
      dec.setEnabled(true);

      if (op.equals("C"))
      {
         numOne = numTwo = 0.0;
         number = "";
         operator = "";
         numDisplay.setText("0.0");
         toggle = 0;
      }
      else if (op.equals("SQRT"))
      {
         double x = Double.parseDouble(numDisplay.getText());
         x = Math.sqrt(x);
         numDisplay.setText(Double.toString(x));
         numOne = x;
      }
      else if (!op.equals("="))
      {
         if (toggle == 0)
         {
            operator = op;
            toggle = 1;
         }
         else if (toggle == 1 && operator.length() == 0 && secondOperator.length() == 0)
         {
            operator = op;
         }
         else if (operator.length() > 0)
         {
            if (operator.equals("+"))
            {
                numOne = numOne + numTwo;
                numDisplay.setText(Double.toString(numOne));
            }
            if (operator.equals("-"))
            {
               numOne = numOne - numTwo;
               numDisplay.setText(Double.toString(numOne));
            }
            if (operator.equals("*"))
            {
               numOne = numOne * numTwo;
               numDisplay.setText(Double.toString(numOne));
            }
            if (operator.equals("/"))
            {
               numOne = numOne / numTwo;
               numDisplay.setText(Double.toString(numOne));
            }
            secondOperator = op;
            operator = "";
         }
         else if (secondOperator.length() > 0)
         {
            if (secondOperator.equals("+"))
            {
               numOne = numOne + numTwo;
               numDisplay.setText(Double.toString(numOne));
            }
            if (secondOperator.equals("-"))
            {
               numOne = numOne - numTwo;
               numDisplay.setText(Double.toString(numOne));
            }
            if (secondOperator.equals("*"))
            {
               numOne = numOne * numTwo;
               numDisplay.setText(Double.toString(numOne));
            }
            if (secondOperator.equals("/"))
            {
               numOne = numOne / numTwo;
               numDisplay.setText(Double.toString(numOne));
            }
            operator = op;
            secondOperator = "";
         }
      }
      else if (op.equals("="))
      {
         if (operator.equals("+") || secondOperator.equals("+"))
         {
            numOne = numOne + numTwo;
            numDisplay.setText(Double.toString(numOne));
         }
         if (operator.equals("-") || secondOperator.equals("-"))
         {
            numOne = numOne - numTwo;
            numDisplay.setText(Double.toString(numOne));
         }
         if (operator.equals("*") || secondOperator.equals("*"))
         {
            numOne = numOne * numTwo;
            numDisplay.setText(Double.toString(numOne));
         }
         if (operator.equals("/") || secondOperator.equals("/"))
         {
            numOne = numOne / numTwo;
            numDisplay.setText(Double.toString(numOne));
         }
         operator = "";
         secondOperator = "";
      }
   }

  /**
   * The zero-parameter constructor.  This builds the calculator GUI.
   **/
   public Calculator()
   {
      setTitle("Calculator");
      setSize(400, 525);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new GridLayout(3, 0));

      one = new NumButton("1");
      two = new NumButton("2");
      three = new NumButton("3");
      four = new NumButton("4");
      five = new NumButton("5");
      six = new NumButton("6");
      seven = new NumButton("7");
      eight = new NumButton("8");
      nine = new NumButton("9");
      zero = new NumButton("0");
      dec = new NumButton(".");
      clear = new OpButton("C");
      clear.setForeground(Color.RED);
      sqroot = new OpButton("SQRT");
      div = new OpButton("/");
      mult = new OpButton("*");
      minus = new OpButton("-");
      plus = new OpButton("+");
      equal = new OpButton("=");

      JPanel levelOne = new JPanel();
      levelOne.setLayout(new GridLayout(2, 0));
      add(levelOne);

      JPanel display = new JPanel();
      display.setLayout(new GridLayout(1, 0));
      levelOne.add(display);

      numDisplay = new JTextField();
      numDisplay.setHorizontalAlignment(JTextField.RIGHT);
      numDisplay.setFont(new Font("Helvetica", Font.BOLD, 40));
      numDisplay.setEditable(false);
      numDisplay.setBackground(Color.YELLOW);
      display.add(numDisplay);

      JPanel topLine = new JPanel();
      topLine.setLayout(new GridLayout(0, 4));
      levelOne.add(topLine);

      topLine.add(clear);
      topLine.add(sqroot);
      topLine.add(div);
      topLine.add(mult);

      JPanel middle = new JPanel();
      middle.setLayout(new GridLayout(2, 4));
      add(middle);

      middle.add(seven);
      middle.add(eight);
      middle.add(nine);
      middle.add(minus);
      middle.add(four);
      middle.add(five);
      middle.add(six);
      middle.add(plus);

      JPanel bottom = new JPanel();
      bottom.setLayout(new GridLayout(0, 2));
      add(bottom);

      JPanel bottomLeft = new JPanel();
      bottomLeft.setLayout(new GridLayout(2, 0));
      bottom.add(bottomLeft);

      JPanel topBL = new JPanel();
      topBL.setLayout(new GridLayout(0, 2));
      bottomLeft.add(topBL);

      topBL.add(one);
      topBL.add(two);

      JPanel bottomBL = new JPanel();
      bottomBL.setLayout(new GridLayout(1, 0));
      bottomLeft.add(bottomBL);

      bottomBL.add(zero);

      JPanel bottomRight = new JPanel();
      bottomRight.setLayout(new GridLayout(0, 2));
      bottom.add(bottomRight);

      JPanel leftBR = new JPanel();
      leftBR.setLayout(new GridLayout(2, 0));
      bottomRight.add(leftBR);

      leftBR.add(three);
      leftBR.add(dec);

      JPanel rightBR = new JPanel();
      rightBR.setLayout(new GridLayout(0, 1));
      bottomRight.add(rightBR);

      rightBR.add(equal);

      numDisplay.setText("0.0");

      setVisible(true);
   }

  /**
   * This class extends the JButton class, and creates pre-initialized
   * number buttons for the calculator.
   **/
   private class NumButton extends JButton
   {
      public NumButton(String s)
      {
         setText(s);
         setFont(new Font("Helvetica", Font.BOLD, 25));
         setActionCommand("num");
         ButtonListener bl = new ButtonListener();
         addActionListener(bl);
      }
   }

  /**
   * This class extends the JButton class, and creates pre-intialized
   * operator buttons for the calculator.
   **/
   private class OpButton extends JButton
   {
      public OpButton(String s)
      {
         setText(s);
         setFont(new Font("Helvetica", Font.BOLD, 25));
         setActionCommand("op");
         setForeground(Color.BLUE);
         ButtonListener bl = new ButtonListener();
         addActionListener(bl);
      }
   }

  /**
   * This class implements the ActionListener class, and determines the
   * action to be performed depending on whether a number button or
   * operator button is pressed.
   **/
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         if(e.getActionCommand().equals("num"))
         {
            doNumber(((JButton)e.getSource()).getText());
         }
         else doOperation(((JButton)e.getSource()).getText());
      }
   }
}
