import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame frame = new JFrame("Two's Complement Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,300);

        JMenuBar menuBar = new JMenuBar();
        JMenu radix = new JMenu("Base");
        JButton exit = new JButton("Exit");
        menuBar.add(radix);
        menuBar.add(exit);

        ButtonGroup bases = new ButtonGroup();
        JRadioButtonMenuItem decimal = new JRadioButtonMenuItem("decimal");
        decimal.setSelected(true);
        JRadioButtonMenuItem hexUnsigned = new JRadioButtonMenuItem("unsigned hexadecimal");
        JRadioButtonMenuItem hex2c = new JRadioButtonMenuItem("two's complement hexadecimal");
        JRadioButtonMenuItem binaryUnsigned = new JRadioButtonMenuItem("unsigned binary");
        JRadioButtonMenuItem binary2c = new JRadioButtonMenuItem("two's complement binary");
        bases.add(decimal);
        bases.add(binaryUnsigned);
        bases.add(binary2c);
        bases.add(hexUnsigned);
        bases.add(hex2c);
        radix.add(decimal);
        radix.add(binaryUnsigned);
        radix.add(binary2c);
        radix.add(hexUnsigned);
        radix.add(hex2c);

        JPanel numberPanel = new JPanel();
        JLabel numberLabel = new JLabel("Enter number:");
        JTextField numberField = new JTextField(16);
        JButton convert = new JButton("Convert");
        numberPanel.add(numberLabel);
        numberPanel.add(numberField);
        numberPanel.add(convert);

        JPanel sizePanel = new JPanel();
        JLabel sizeLabel = new JLabel("Enter two's complement bit size:");
        JTextField sizeField = new JTextField(2);
        sizePanel.add(sizeLabel);
        sizePanel.add(sizeField);

        JTextArea textArea = new JTextArea();
        textArea.setRows(5);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.EAST, numberPanel);
        frame.getContentPane().add(BorderLayout.WEST, sizePanel);
        frame.getContentPane().add(BorderLayout.SOUTH, textArea);

        exit.addActionListener(e -> System.exit(0));
        convert.addActionListener(e -> convert(textArea));

        frame.setVisible(true);
    }

    private static void convert(JTextArea textArea) {
        textArea.append("test0\n");
        textArea.append("test1\n");
    }
}
