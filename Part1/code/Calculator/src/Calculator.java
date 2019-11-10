import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame 
{
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private ActionListener myListener;
    private static final int CONTAINER_LENGTH = 5;
    private static final int CONTAINER_HEIGHT = 5;
    private static final int FONTSIZE1 = 18;
    private static final int FONTSIZE2 = 15;
    private static final int ROW = 4;
    private static final int COL = 4;
    private static final int LENGTH = 5;
    private static final int HEIGHT = 5;
    private static final int SIZE = 300;
    private static final int GAP = 7;

    public Calculator() 
    {
        setTitle("Calculator");
        setSize(SIZE, SIZE);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new BorderLayout(CONTAINER_LENGTH, CONTAINER_HEIGHT));
        JPanel pnlNorth = new JPanel();
        JPanel pnlCenter = new JPanel();
        container.add(pnlNorth, BorderLayout.NORTH);
        container.add(pnlCenter, BorderLayout.CENTER);
  
        textField = new JTextField();
        textField.setFont(new Font("宋体", Font.PLAIN, FONTSIZE1));
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);

        pnlNorth.setLayout(new BorderLayout(LENGTH, HEIGHT));
        pnlNorth.add(textField, BorderLayout.CENTER);
        String[] names = 
        {
            "7", "8", "9", "+", 
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", ".", "/", "="
        };
        pnlCenter.setLayout(new GridLayout(ROW, COL, GAP, GAP));
        for (int i = 0; i < names.length; i ++) 
        {
            JButton btnNum = useButton(names[i]);
            btnNum.setFont(new Font("微软雅黑",Font.PLAIN, FONTSIZE2));
            pnlCenter.add(btnNum);
        }
    }

    private JButton useButton(String names) 
    {
        JButton button = new JButton(String.valueOf(names));
        button.setFocusPainted(false);
        if (myListener == null)
        {
            myListener = new ActionListener()
            {
                public void actionPerformed(ActionEvent event)
                {
                      String btnInformation = event.getActionCommand();
                    char key2 = btnInformation.charAt(0);
                    action(key2);
                }
            };
        }
        button.addActionListener(myListener);
        return button;
    }

    private int len;
    private char exit = '0';
    private char op = 'n';
    private String a, b, value;
    private boolean needClear = false;
    private boolean hasError = false;

    private void action(char key) 
    {
        switch(key) 
        {
            case '0':
                handleCase2(key);
                break;

            case '.':   
                handleCase3(key);
                break;

            case '+': 
            case '-':
            case '*':
            case '/':
                handleCase4(key);
                break;
            
            case '=':
                handleCase5();
                break;

            default: 
                handleCase1(key);
                break;
        }
    }

    // 1~9
    private void handleCase1(char key)
    {
        if (hasError)
        {
            textField.setText("");
            textField.setText(String.valueOf(key));
            hasError = false;
        }
        if (needClear) 
        {
            textField.setText("");
            textField.setText(String.valueOf(key));
            needClear = false;
        } 
        else 
        {
            if (textField.getText().equals("0")) {
                textField.setText(String.valueOf(key));
            }
            else
            {
                String text = textField.getText() + key;
                textField.setText(text);
            }
        }
    }

    // 0
    private void handleCase2(char key)
    {
        if (hasError)
        {
            textField.setText("");
            textField.setText(String.valueOf(key));
            hasError = false;
        }
        if (textField.getText().equals("0")) {
            return;
        }
        else if (needClear) 
        {
            textField.setText("");
            textField.setText(String.valueOf(key));
            needClear = false;
        } 
        else
        {
            String text = textField.getText() + key;
            textField.setText(text);
        }
    }

    // .
    private void handleCase3(char key)
    {
        if (textField.getText().equals("")) {
            return;
        }
        else
        {
            String text = textField.getText();
            len = text.length();
            int i = 0;
            for (; i < len; i ++)
            {
                if (text.charAt(i) == '.') {
                    return;
                }
            }
            if (i < len) {
                return;
            }
            text = textField.getText() + key;
            textField.setText(text);
        }
    }

    // + - * /
    private void handleCase4(char key)
    {
        if (!textField.getText().equals("") && !hasError)
        {
            if (op == 'n') {
                a = textField.getText();
            }
            else 
            {
                b = textField.getText();
                calculation();
                a = value;
                b = "";
                textField.setText(value);
            }
            op = key;
            needClear = true;
            exit = '1';
        }
    }

    // =
    private void handleCase5()
    {
        if (exit == '0')
        {
            textField.setText("");
            return;
        }
        if (textField.getText().equals("")) {
            return;
        }
        b = textField.getText();
        calculation();
        Character l1 = value.charAt(value.length() - 1);
        Character l2 = value.charAt(value.length() - 2);
        if (l1.equals('0') && l2.equals('.')) {
            value = value.substring(0, value.length() - 2);
        }
        textField.setText(String.valueOf(value));
        needClear = true;
    }

    private void calculation()
    {
        double v1 = Double.parseDouble(a);
        double v2 = Double.parseDouble(b);
        double result = 0;
        switch(op)
        {
            case '+':
                result = v1 + v2;
                break;
                
            case '-':
                result = v1 - v2;
                break;

            case '*':
                result = v1 * v2;
                break;

            case '/':
                if (v2 == 0) 
                {
                    hasError = true;
                    break;
                }
                result = v1 / v2;
                break;
        }
        a = "";
        b = "";
        exit = '0';
        op = 'n';
        if (hasError) {
            value = "Error!";
        }
        else {
            value = String.valueOf(result);
        }
    }

    public static void main(String[] args) 
    {
        new Calculator().setVisible(true);
    }
}
